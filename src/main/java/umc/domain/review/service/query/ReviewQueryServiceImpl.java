package umc.domain.review.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.code.MemberErrorCode;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.code.ReviewErrorCode;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.dto.res.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.ReviewException;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.code.StoreErrorCode;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.repository.StoreRepository;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
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

    @Override
    public PageResponseDTO<ReviewResDTO.ReviewPreViewDTO> findReview(
        String storeName,
        Integer page
    ){
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(()-> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<Review> result = reviewRepository.findAllByStore(store,pageRequest);
        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        List<ReviewResDTO.ReviewPreViewDTO> reviewList = result
                .map(ReviewConverter::toReviewPreviewDTO)   // 반드시 PreViewDTO 변환 메서드 사용
                .getContent();

        return new PageResponseDTO<>(
                reviewList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.hasNext()
        );
    }

    @Override
    public PageResponseDTO<ReviewResDTO.MemberReviewDTO> findMemberReview(
            Long memberId, Integer page
    ){
        // 1. 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 페이징 설정
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. 회원 리뷰 조회
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        if (result.isEmpty()) {
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        // 4. DTO 변환
        List<ReviewResDTO.MemberReviewDTO> reviewList = result
                .map(ReviewConverter::toMemberReviewDTO)
                .getContent();

        // 5. PageResponseDTO 반환
        return new PageResponseDTO<>(
                reviewList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.hasNext()
        );
    }

}
