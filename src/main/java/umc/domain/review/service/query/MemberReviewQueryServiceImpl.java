package umc.domain.review.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.MemberReviewConverter;
import umc.domain.review.dto.MemberReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class MemberReviewQueryServiceImpl implements MemberReviewQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public MemberReviewResDTO.ReviewPreViewListDTO findReview(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        PageRequest pageRequest = PageRequest.of(page - 1, 5);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        return MemberReviewConverter.toReviewPreviewListDTO(result);
    }

}
