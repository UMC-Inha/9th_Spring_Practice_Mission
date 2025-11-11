package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/search") //워크북 실습용 API
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){

        //서비스에게 요청
        List<Review> result = reviewService.searchReview(query,type);
        return result;
    }

    //과제(미션)용 API
    @GetMapping("/my")
    public List<ReviewResponseDTO.ReviewPreviewDTO>getMyReviews(
            @RequestParam(name = "userId")Long userId,
            @RequestParam(name = "storeId", required = false) Long storeId,
            @RequestParam(name = "starFloor", required = false) Integer starFloor
    ){
        List<ReviewResponseDTO.ReviewPreviewDTO> myReviews = reviewService.getMyReviews(userId,storeId,starFloor);
        return myReviews;
    }
}
