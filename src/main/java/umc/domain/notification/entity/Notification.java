package umc.domain.notification.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.notification.enums.AlarmType;
import umc.domain.user.entity.User;
import umc.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "notifications")
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private AlarmType type;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="body",nullable = false)
    private String body;

    @Column(name="is_read",nullable = false)
    private Boolean isRead;



}
