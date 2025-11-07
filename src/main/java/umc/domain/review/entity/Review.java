package umc.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.Member;
import umc.domain.store.entity.Store;
import umc.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")


public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="content")
    private String content;

    @Column(name = "star")
    private Float star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "review")
    private List<Review_photo> photos= new ArrayList<>();

    @OneToOne(mappedBy = "review" )
    private Reply reply;

    @Builder
    public Review(String content, Float star, Member member, Store store) {
        this.content = content;
        this.star = star;
        this.member = member;
        this.store = store;
    }
    public static Review createReview(String content, Float star, Member member, Store store) {
        return Review.builder()
                .content(content)
                .star(star)
                .member(member)
                .store(store)
                .build();
    }


}
