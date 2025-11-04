package umc.domain.review.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {


    private ReviewRepository reviewRepository;

    public Page<Review> findMyReview(Long memberId,
                                     Long storeId, Integer rateFilter,
                                     Boolean sortByStore, Boolean sortByRate, int page, int size){
        Pageable pageable = PageRequest.of(page, size);


        return reviewRepository.findMyReviews(memberId,storeId,rateFilter, sortByStore, sortByRate, pageable);
    }

}
