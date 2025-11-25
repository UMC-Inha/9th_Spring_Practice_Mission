package umc.domain.review.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {


    private ReviewRepository reviewRepository;
    private MemberRepository memberRepository;
    private StoreRepository storeRepository;

    public Page<Review> findMyReview(Long memberId,
                                     Long storeId, Integer rateFilter,
                                     Boolean sortByStore, Boolean sortByRate, int page, int size){
        Pageable pageable = PageRequest.of(page, size);


        return reviewRepository.findMyReviews(memberId,storeId,rateFilter, sortByStore, sortByRate, pageable);
    }


    public Long createReview(Long memberId, Long storeId,
                               Integer rate, String discription, String photo) {


        //예외 던지기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .rate(rate)
                .discription(discription)
                .photo(photo)
                .build();


        Review myReview = reviewRepository.save(review);

        // convert to dto
        return myReview.getId();

    }

}
