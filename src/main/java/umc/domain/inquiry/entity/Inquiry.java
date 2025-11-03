package umc.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.inquiry.enums.InquiryStatus;
import umc.global.entity.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "inquiry")
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

}
