package umc.domain.inquery.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="inquiry_replies")
public class InquiryReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inquiry_id")
    private CustomerInquiry customerInquiry;

    //관리자 id . 아직 관리자 미구현

    @Column(name="content",nullable = false)
    private String content;
}
