package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> , ReviewQueryDsl{

    //save 구현 필요 X 이미 있는 함수. 추후 생길 Service Layer에서 reviewRepository.save(newReview) 형태로 리뷰 insert


}
