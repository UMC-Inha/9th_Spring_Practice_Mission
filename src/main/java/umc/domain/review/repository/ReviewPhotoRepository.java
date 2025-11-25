package umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.ReviewPhoto;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {
}
