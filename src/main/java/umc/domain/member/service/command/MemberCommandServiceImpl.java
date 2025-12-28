package umc.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.food.repository.FoodRepository;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.req.MemberReqDTO;
import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.repository.MemberFoodRepository;
import umc.domain.member.repository.MemberRepository;
import umc.global.auth.enums.Role;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MemberResponseDTO.SignupResponseDTO signup(
            MemberReqDTO.SignupRequestDTO dto
    ){
        String salt = passwordEncoder.encode(dto.password());
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);
        memberRepository.save(member);


        if (!dto.preferCategory().isEmpty()){
            List<MemberFood> memberFood = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.getReferenceById(id))
                            .build()
                    )
                    .collect(Collectors.toList());

            memberFoodRepository.saveAll(memberFood);
        }
        return MemberConverter.toSignupDTO(member);
    }
}
