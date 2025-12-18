package umc.domain.review.service.query;

import java.util.List;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.dto.ReviewResDTO.SearchDTO;

public interface ReviewQueryService {

    List<SearchDTO> searchReview(Long memberId, ReviewReqDTO.SearchDTO request);

    ReviewResDTO.ReviewPreViewListDTO findReview( String storeName, Integer page);

}
