package umc.domain.review.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;
import umc.domain.photo.entity.Photo;
import umc.domain.store.entity.Store;
import umc.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Reviewreply> reviewreplyList =  new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review",cascade = CascadeType.REMOVE)
    private List<Photo> photos = new ArrayList<>();

}
