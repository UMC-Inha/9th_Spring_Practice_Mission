package umc.domain.inquery.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="inquiry_photos")
public class InquiryPhoto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="inquiry_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerInquiry customerInquiry;

    @Column(name="image_url")
    private String imageUrl;
}
