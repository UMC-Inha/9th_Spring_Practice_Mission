package umc.domain.review.entity;
import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.entity.Store;
import umc.domain.user.entity.Member;
import umc.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Integer rate;

    @Column
    private String photo;

    @Column
    private String discription;
}
