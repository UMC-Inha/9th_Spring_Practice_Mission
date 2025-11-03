package umc.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    2. 마이페이지 화면 쿼리
//    select u.id, u.name, u.email, u.phone_num, u.point, u.phone_verified
//    from users as u
//    where u.id = '어떤 유저'

    //이 조차 findById로 해결됨. User 객체를 받아서 user.getEmail() 이런식을 호출해서 정보 얻을 수 있음.
    Optional<User> findUserById(Long id); //명시적으로 선언해서 쓰고싶다면 왼쪽 코드가 findById와 같은 기능을 함.
    // 같은 기능을 하기에 굳이 위 코드를 쓸 필욘 없음.

}
