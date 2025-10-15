package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.enums.Day;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "office_time")
public class Officetime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
    private Day day;

    @Column(name = "start_time",  nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time",  nullable = false)
    private LocalDateTime endTime;

    @Column(name = "break_start_time")
    private LocalDateTime breakStartTime;

    @Column(name = "break_end_time")
    private LocalDateTime breakEndTime;

    @Column(name = "last_order_time")
    private LocalDateTime lastOrderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
