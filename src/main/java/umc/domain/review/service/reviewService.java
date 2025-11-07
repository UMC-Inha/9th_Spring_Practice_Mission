package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.entity.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class reviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository; // 주입
    private final StoreRepository storeRepository;   // 주입

    @Transactional // 데이터를 변경하므로 readOnly=false (기본값)
    public Review createReview(String content, Float star, Long memberId, Long storeId) {

        // 1. 연관된 엔티티(Member, Store)를 조회합니다.
        // findById() 대신 getReferenceById()를 사용하면
        // 실제 SELECT 쿼리 없이 프록시 객체를 가져와 FK로만 사용할 수 있어 효율적입니다.
        Member member = memberRepository.getReferenceById(memberId);
        Store store = storeRepository.getReferenceById(storeId);

        // 2. Review 엔티티를 생성합니다. (위에서 만든 정적 팩토리 메소드 사용)
        Review newReview = Review.createReview(content, star, member, store);

        // 3. save() 메소드를 호출하여 저장합니다.
        // createdAt, updatedAt은 Auditing 기능으로 자동 저장됩니다.
        return reviewRepository.save(newReview);
    }
}