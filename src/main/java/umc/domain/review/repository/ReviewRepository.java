package umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewQueryDsl {
    List<Review> findByStoreId(Long storeId);
    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findAllByMember(Member member, Pageable pageable);


}
