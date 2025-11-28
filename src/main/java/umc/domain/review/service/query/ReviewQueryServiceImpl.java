package umc.domain.review.service.query;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.store.StoreException;
import umc.domain.store.exception.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<ReviewDto> searchReviewByMember(Long memberId, String storeName, BigDecimal starRating) {
        return reviewRepository.searchReviewByMemberId(memberId, storeName, starRating);
    }

    @Override
    public ReviewResDTO.ReviewPreviewListDTO findReview(
            String storeName,
            Integer page
    ) {
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
