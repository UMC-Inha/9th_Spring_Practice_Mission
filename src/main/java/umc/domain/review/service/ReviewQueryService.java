package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.review.code.ReviewErrorCode;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.ReviewException;
import umc.domain.review.repository.ReviewRepository;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public PageResponseDTO<ReviewResponseDTO> searchReview(Long memberId, String type, String query, Pageable pageable) {
        Page<Review> reviews = reviewRepository.searchReview(memberId, type, query, pageable);

        // ✅ 검색 결과가 없으면 예외 던짐
        if (reviews.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        List<ReviewResponseDTO> list = reviews
                .map(ReviewConverter::toResponseDTO)
                .getContent();

        return new PageResponseDTO<>(
                list,
                reviews.getNumber(),
                reviews.getSize(),
                reviews.getTotalElements(),
                reviews.getTotalPages(),
                reviews.hasNext()
        );
    }
}
