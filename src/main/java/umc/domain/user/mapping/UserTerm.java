package umc.domain.user.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.user.entity.User;
import umc.domain.term.entity.Term;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="user_terms")
public class UserTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name="term_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Term term;
}
