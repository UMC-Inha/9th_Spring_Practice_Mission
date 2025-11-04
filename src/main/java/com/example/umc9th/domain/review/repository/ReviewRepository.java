package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 유제 id, 가게 id, 별점, 날짜, 리뷰 내용,

public interface ReviewRepository extends JpaRepository<Review, Long> {
    /* 리뷰 작성 쿼리
    INSERT INTO review(mission_id, store_id, star,text)
    VALUES(1,100,5,’음 너무 너무 맛있어요 포인트도 얻고 맛있는
    맛집도 알게 된 것 같아 너무나도 행복한 식사였답니다. 다음에 또 올게요!!’)
     */


    // 1번 : 메서드 이름으로 쿼리 생성 , 가게 id에 따라서 달라지는 리뷰 조회라고 생각
    // List<Review> findAllByStore(Long storeID);

    // 2번 : @Query 사용,가게 ID 전달을 통해 별점, 내용, 리뷰 내용 담긴 Review 엔티티 가져온다
    // 리뷰 목록 최신순으로 보여줌 (내림차순)
    // 닉네임 표시과정에서 N+1 문제가 발생할 수도 있어서 fetch join
    @Query("SELECT r FROM Review r JOIN FETCH r.member m WHERE r.store.id = :storeID ORDER BY r.createdAt DESC")
    List<Review> findAllByStore(@Param("storeID") Long storeID);
}
