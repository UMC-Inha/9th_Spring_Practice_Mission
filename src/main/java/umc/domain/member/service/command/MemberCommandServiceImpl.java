package umc.domain.member.service.command;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.exception.food.FoodException;
import umc.domain.member.exception.food.code.FoodErrorCode;
import umc.domain.member.repository.FoodRepository;
import umc.domain.member.repository.MemberFoodRepository;
import umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    @Override
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ) {
        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        if (dto.preferCategory().size() > 1) {
            List<MemberFood> memberFoods = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)) // 음식이 없으면 예외 발생
                            )
                            .build()
                    ).toList();

            memberFoodRepository.saveAll(memberFoods);
        }

        return MemberConverter.toJoinDTO(member);
    }
}

