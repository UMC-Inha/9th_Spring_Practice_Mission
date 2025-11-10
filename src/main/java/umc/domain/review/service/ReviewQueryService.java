package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;


@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewResponseDTO> searchReview(Long memberId, String type, String query, Pageable pageable) {
        Page<Review> reviews = reviewRepository.searchReview(memberId, type, query, pageable);

        return reviews.map(r -> ReviewResponseDTO.builder()
                .id(r.getId())
                .content(r.getContent())
                .grade(r.getGrade())
                .storeName(r.getStore().getName())
                .createdAt(r.getCreatedAt())
                .build());
    }
}
