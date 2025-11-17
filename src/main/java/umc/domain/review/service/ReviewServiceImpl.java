package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.req.MyReviewReqDTO;
import umc.domain.review.dto.res.MyReviewResDTO;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.review.repository.query.MyReviewQuery;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl  implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Page<MyReviewResDTO> getMyReviews(Long memberId, MyReviewReqDTO reqDTO, Pageable pageable) {

        MyReviewQuery query = ReviewConverter.toMyReviewQuery(reqDTO);

        return reviewRepository.findMyReviews(memberId, query, pageable);
    }
}
