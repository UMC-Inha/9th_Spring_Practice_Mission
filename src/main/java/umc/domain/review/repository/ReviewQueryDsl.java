package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import java.util.List;
import umc.domain.review.dto.ReviewResDTO.SearchDTO;

public interface ReviewQueryDsl {

    List<SearchDTO> searchReview(Predicate predicate);
}
