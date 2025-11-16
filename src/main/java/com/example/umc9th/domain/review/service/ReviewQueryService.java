package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QRegion;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreErrorCode;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.example.umc9th.domain.review.dto.res.ReviewResDTO.*;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 워크북 내용
    public List<Review> searchReview(String query, String type)
    {
        QReview review = QReview.review;
        QRegion region = QRegion.region;

        BooleanBuilder builder = new BooleanBuilder();

        if(type.equals("location")){
            builder.and(region.name.contains(query));
        }
        if(type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")){
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(region.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList;
    }

    // 미션
    public List<ReviewDTO> getReviews(Long storeId, Float star) {

        // 1. store 유무 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. review 찾기
        List<Review> filterList = reviewRepository.filterReview(store.getId(), star);

        // 3. Converter로 변환해서 return.
        return ReviewConverter.toDTOList(filterList);
    }

    public ReviewDTO convertDTO(Review review) {
        return ReviewDTO.builder()
                .storeId(review.getId())
                .star(review.getStar())
                .createdDate(review.getCreatedAt().toLocalDate())
                .content(review.getContent()).build();
    }
}
