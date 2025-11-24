package umc.domain.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.auth.converter.AuthConverter;
import umc.domain.auth.dto.req.AuthReqDTO;
import umc.domain.auth.dto.res.AuthResDTO;
import umc.domain.food.entity.Food;
import umc.domain.food.exception.FoodException;
import umc.domain.food.exception.code.FoodErrorCode;
import umc.domain.food.repository.FoodRepository;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.repository.MemberFoodRepository;
import umc.domain.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class SignUpServiceImpl implements SignUpService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public AuthResDTO.SignUpDTO signup(
            AuthReqDTO.SignUpDTO dto
    ) { // 사용자 생성
        Member member = AuthConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1) {
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()) {

                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }


        // 응답 DTO 생성
        return AuthConverter.toSIgnUpDTO(member);
    }
}