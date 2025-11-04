package umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    // 이 안에 save 메소드가 자동으로 포함되어 있다


}
