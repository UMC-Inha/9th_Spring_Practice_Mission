package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.converter.ReviewCreateConverter;
import com.example.umc9th.domain.review.dto.ReviewCreateDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreErrorCode;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCreateService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public Long createReview(ReviewCreateDTO reviewCreateDTO) {

        Store store = storeRepository.findById(reviewCreateDTO.getStoreId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewCreateConverter.toReview(reviewCreateDTO, store);

        reviewRepository.save(review);

        return review.getId();
    }
}
