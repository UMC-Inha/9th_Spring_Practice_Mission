package umc.domain.inquiry.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;
import umc.domain.photo.entity.Photo;
import umc.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "inquiry")
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "content", nullable = false, length = 250)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.REMOVE)
    private List<Inquiryreply> inquiryreplies = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "inquiry",  cascade = CascadeType.REMOVE)
    private List<Photo> photos = new ArrayList<>();
}
