package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.enums.Type;
import umc.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "arrive_time", nullable = false)
    private LocalDateTime arriveTime;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
}
