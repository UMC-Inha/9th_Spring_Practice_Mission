package umc.domain.member.service.common;

import static umc.domain.member.exception.code.MemberErrorCode.NOT_FOUND_MEMBER;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.inquiry.repository.InquiryRepository;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Food;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.Policy;
import umc.domain.member.entity.PolicyType;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberPolicy;
import umc.domain.member.exception.FoodException;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.PolicyException;
import umc.domain.member.exception.code.FoodErrorCode;
import umc.domain.member.exception.code.PolicyErrorCode;
import umc.domain.member.repository.FoodRepository;
import umc.domain.member.repository.MemberFoodRepository;
import umc.domain.member.repository.MemberPolicyRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.PolicyRepository;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.review.repository.ReviewPhotoRepository;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final InquiryRepository inquiryRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    private final PolicyRepository policyRepository;
    private final MemberPolicyRepository memberPolicyRepository;

    @Transactional
    @Override
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ) {

        Member member = MemberConverter.toMember(dto);

        memberRepository.save(member);

        // 약관 동의 처리
        if(dto.agreements()== null || dto.agreements().isEmpty()){
            throw new PolicyException(PolicyErrorCode.REQUIRED_POLICY_NOT_ACCEPTED);
        }

        Map<Long, Boolean> agreementMap = dto.agreements().stream()
                .collect(Collectors.toMap(
                        MemberReqDTO.AgreementDTO::policyId,
                        MemberReqDTO.AgreementDTO::agreed
                ));

        List<Policy> policies = policyRepository.findAllById(agreementMap.keySet());

        if (policies.size() != agreementMap.size()) {
            throw new PolicyException(PolicyErrorCode.NOT_FOUND_POLICY);
        }

        boolean allRequiredAccepted = policies.stream()
                .filter(p -> p.getType() == PolicyType.REQUIRED)
                .allMatch(p -> Boolean.TRUE.equals(agreementMap.get(p.getId())));

        if (!allRequiredAccepted) {
            throw new PolicyException(PolicyErrorCode.REQUIRED_POLICY_NOT_ACCEPTED);
        }

        List<MemberPolicy> memberPolicies = policies.stream()
                .map(policy -> MemberPolicy.builder()
                        .member(member)
                        .policy(policy)
                        .build()
                )
                .toList();

        memberPolicyRepository.saveAll(memberPolicies);


        // 선호 음식 매핑
        if (dto.preferredFoods() != null && !dto.preferredFoods().isEmpty()) {

            List<Long> foodIds = dto.preferredFoods();
            List<Food> foods = foodRepository.findAllById(foodIds);

            if (foods.size() != foodIds.size()) {
                throw new FoodException(FoodErrorCode.NOT_FOUND_FOOD);
            }

            List<MemberFood> memberFoods = foods.stream()
                    .map(food -> MemberFood.builder()
                            .member(member)
                            .food(food)
                            .build())
                    .toList();

            memberFoodRepository.saveAll(memberFoods);
        }

        return MemberConverter.toJoinDTO(member);
    }

    @Override
    @Transactional
    public void updateMemberName(Long memberId, String name) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
        member.updateName(name);

    }

    @Override
    @Transactional
    public void deleteMember(Long memberId, boolean hard) {

        if (hard) {
            hardDeleteMember(memberId);
        } else {
            softDeleteMember(memberId);
        }
    }

    private void hardDeleteMember(Long memberId) {

        int deletedPhotos = reviewPhotoRepository.deleteAllByMemberId(memberId);
        int deletedReviews = reviewRepository.deleteAllByMemberId(memberId);
        int deletedMissions = memberMissionRepository.deleteAllByMemberId(memberId);
        int deletedPolicies = memberPolicyRepository.deleteAllByMemberId(memberId);
        int deletedInquiries = inquiryRepository.deleteAllByMemberId(memberId);

        int deletedUsers = memberRepository.hardDeleteById(memberId);

        int total =
                deletedPhotos + deletedReviews + deletedMissions + deletedPolicies + deletedInquiries + deletedUsers;

        if (total == 0) {
            log.info("Member {} delete is idempotent: nothing to delete.", memberId);
        } else {
            log.info("Member {} hard-deleted: photos={}, reviews={}, missions={}, policies={}, inquiries={}, users={}",
                    memberId, deletedPhotos, deletedReviews, deletedMissions, deletedPolicies, deletedInquiries,
                    deletedUsers);
        }
    }

    private void softDeleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));

        member.softDelete();

    }

}
