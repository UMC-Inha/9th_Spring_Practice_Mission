package umc.domain.inquery.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.inquery.enums.InquiryCategory;
import umc.domain.inquery.enums.InquiryStatus;
import umc.domain.user.entity.User;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="customer_inquiries")
public class CustomerInquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="body", columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    private InquiryCategory category;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;
}
