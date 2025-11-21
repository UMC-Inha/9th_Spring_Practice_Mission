package umc.domain.review.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.domain.user.entity.User;
import umc.domain.user.exception.UserException;
import umc.domain.user.exception.code.UserErrorCode;
import umc.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewResDTO.AddReviewResDTO addReview(ReviewReqDTO.AddReviewDTO req){

        User user = userRepository.findById(1L).orElseThrow(()-> new UserException(UserErrorCode.NOT_FOUND));
        //로그인 기능 미구현으로 하드코딩 했습니다
        Store store = storeRepository.findById(req.storeId()).orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(req,user,store);

        reviewRepository.save(review);

        return ReviewConverter.toAddReviewResDTO(review);
    }
}
