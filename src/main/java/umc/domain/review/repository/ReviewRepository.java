package umc.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.domain.member.entity.Member;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Review r where r.member.id = :memberId")
    int deleteAllByMemberId(@Param("memberId") Long memberId);

    Page<Review> findAllByStore(Store store, Pageable pageable);

    @EntityGraph(attributePaths = {"reviewReply", "photos"})
    Page<Review> findAllByMember(Member member, Pageable pageable);

}
