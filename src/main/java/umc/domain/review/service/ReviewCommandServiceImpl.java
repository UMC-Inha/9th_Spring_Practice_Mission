package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.repository.StoreRepository;
import umc.global.apiPayload.Exception.GeneralException;
import umc.global.apiPayload.code.GeneralErrorCode;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewInfo writeReview(
            Long memberId,
            Long storeId,
            ReviewReqDTO.CreateReview req
    ) {
        // 1) 회원 조회 (삭제되지 않은 회원만)
        Member member = memberRepository.findByIdAndDeletedFalse(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2) 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 3) 리뷰 엔티티 생성
        Review review = ReviewConverter.toReview(req, member, store);

        // 4) 저장
        Review saved = reviewRepository.save(review);

        // 5) 응답 DTO 변환
        return ReviewConverter.toReviewInfo(saved);
    }
}
