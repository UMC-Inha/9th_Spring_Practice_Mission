package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QRegion;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final RestClient.Builder builder;

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
        List<Review> filterList = reviewRepository.filterReview(storeId, star);

        return filterList.stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO convertDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .star(review.getStar())
                .createdDate(review.getCreatedAt().toLocalDate())
                .content(review.getContent()).build();
    }

}
