package umc.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.code.ReviewErrorCode;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.ReviewException;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResDTO.CreateResDTO createReview(
            ReviewReqDTO.CreateReviewDTO dto
    ){
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .grade(dto.grade())
                .content(dto.content())
                .member(member)
                .store(storeRepository.getReferenceById(dto.storeId()))
                .build();

        reviewRepository.save(review);

        return ReviewConverter.toResDTO(review);
    }

}
