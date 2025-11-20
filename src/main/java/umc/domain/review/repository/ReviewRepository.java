package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> , ReviewQueryDsl{
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);



}
