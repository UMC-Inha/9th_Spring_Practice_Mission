package umc.domain.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.user.enums.MissionStatus;
import umc.domain.user.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission,Long> {
//    3. 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
//    select um.id, um.status, m.id, m.reward_points, m.base_amount
//, s.id, s.store_name
//    from user_missions as um
//    join missions m on m.id = um.mission_id
//    join stores s on s.id = m.store_id
//    where um.user_id = '어떤 사용자'
//    and um.status = 'IN_PROGRESS' --완료 상태를 볼때는 여길 'SUCCESS'로 변경
//    and um.updated_at<? or (um.updated_at = ? and um.id<?) --최신페이지는 이거 삭제
//    order by um.updated_at desc, um.id desc
//    limit 5; --5페이지씩 보겠다

    @Query(value = "SELECT um FROM UserMission um " +
            "JOIN FETCH um.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE um.user.id = :userId AND um.status = :status",
        countQuery = "SELECT COUNT(um) FROM UserMission um " +
                    "WHERE um.user.id = :userId AND um.status = :status") //전체 데이터 개수 세기
    Page<UserMission> findUserMissionsByStatus(@Param("userId") Long userId,
                                               @Param("status")MissionStatus status,
                                               Pageable pageable
    );
    //JOIN FETCH가 없다면 N+1 문제 발생
    //UserMission 5개를 조회했을때 N만큼 미션 정보 불러옴 -> 1+N
    //countQuery는 페이징하려면 전체 데이터가 총 몇개인지 알아야 할 수 있음.
    //부가적인 페이징 정보는 Pageable에 담겨서 옴

//    //미션 진행도
//    select mod(count(*),10)
//    from user_missions um
//    join missions m on m.id = um.mission_id
//    join stores s on s.id = m.store_id
//    where um.user_id = '어느사용자'
//    and um.status = 'SUCCESS'
//    and s.region = '어떤지역';

    @Query("SELECT COUNT(um) FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user.id = :userId " +
            "AND um.status = 'SUCCESS' " +
            "AND s.region.id = :regionId ")
    Long countSuccessMissionsInRegion(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId
    );
}
