package umc.domain.review.service.command;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.member.MemberException;
import umc.domain.member.exception.member.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.entity.ReviewPhoto;
import umc.domain.review.repository.ReviewPhotoRepository;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.store.StoreException;
import umc.domain.store.exception.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public ReviewResDTO.ReviewCreateDTO createReview(Long storeId, Long memberId,
                                                     ReviewReqDTO.ReviewCreateDTO reviewCreateDTO) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Review review = ReviewConverter.toReview(store, member, reviewCreateDTO);

        reviewRepository.save(review);
        List<ReviewPhoto> reviewPhotos = reviewCreateDTO.photos().stream()
                .map(photoUrl -> ReviewPhoto.builder()
                        .photoUrl(photoUrl)
                        .review(review)
                        .build())
                .toList();

        reviewPhotoRepository.saveAll(reviewPhotos);

        return ReviewConverter.toReviewEnrollDTO(review, reviewCreateDTO.photos());
    }
}
