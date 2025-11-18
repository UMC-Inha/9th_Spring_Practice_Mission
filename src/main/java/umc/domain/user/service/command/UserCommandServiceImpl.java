package umc.domain.user.service.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.food.entity.Food;
import umc.domain.food.exception.FoodException;
import umc.domain.food.exception.code.FoodErrorCode;
import umc.domain.food.repository.FoodRepository;
import umc.domain.user.converter.UserConverter;
import umc.domain.user.dto.req.UserReqDTO;
import umc.domain.user.dto.res.UserResDTO;
import umc.domain.user.entity.User;
import umc.domain.user.mapping.UserFood;
import umc.domain.user.repository.UserFoodRepository;
import umc.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final UserFoodRepository userFoodRepository;

    //회원가입 로직
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(UserReqDTO.JoinDto joinDto){

        //사용자 생성
        User user = UserConverter.toUser(joinDto);
        //DB 적용
        userRepository.save(user);

        //선호 음식 존재 여부 확인
        if(joinDto.preferCategory().size()>1) {

            //for문 버전
//            List<UserFood> userFoodList = new ArrayList<>();
//
//            //선호 음식 ID별 조회
//            for(Long foodId : joinDto.preferCategory()){
//
//                //음식 존재 여부 검증
//                Food food = foodRepository.findById(foodId).orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));
//
//                //UserFood 엔티티 생성
//                UserFood userFood = UserFood.builder()
//                        .user(user)
//                        .food(food)
//                        .build();
//
//                //사용자 - 음식 (선호 음식) 추가
//                userFoodList.add(userFood);
//        }
            List<UserFood> userFood = joinDto.preferCategory().stream()
                    .map(id -> UserFood.builder()
                            .user(user)
                                    .food(foodRepository.findById(id).orElseThrow(()-> new FoodException(FoodErrorCode.NOT_FOUND)))
                                            .build()
                    ).collect(Collectors.toList());
            //모든 선호 음식 추가 : DB 적용
            userFoodRepository.saveAll(userFood);
        }

        //응답 DTO 생성
        return UserConverter.toJoinDTO(user);
    }

}
