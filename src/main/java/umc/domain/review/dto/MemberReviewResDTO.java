package umc.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public class MemberReviewResDTO {

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            BigDecimal score,
            String body,
            List<String> photoUrls,
            LocalDate createdAt,
            ReviewReplyDTO reviewReply
    ){}

    @Builder
    public record ReviewReplyDTO(

            String body,
            LocalDate createdAt
    ){

    }
}

