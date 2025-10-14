package umc.domain.term.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.term.enums.TermName;
import umc.global.entity.BaseEntity;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name="terms")
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="term_name", nullable = false)
    private TermName name;

    @Column(name="is_required", nullable = false)
    private Boolean isRequired;


}
