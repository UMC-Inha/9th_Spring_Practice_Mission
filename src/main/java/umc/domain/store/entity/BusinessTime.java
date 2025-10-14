package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.enums.Day;
import umc.global.entity.BaseEntity;

import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="business_times")
public class BusinessTime extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name="end_time",nullable = false)
    private LocalTime endTime;

    @Column(name="break_start_time", nullable = false)
    private LocalTime breakStartTime;

    @Column(name="break_end_time",nullable = false)
    private LocalTime breakEndTime;

    @Column(name="last_order_time",nullable = false)
    private LocalTime lastOrderTime;

    @Column(name="day", nullable = false)
    @Enumerated(EnumType.STRING)
    private Day day;

    @Column(name="is_closed", nullable = false)
    private boolean isClosed;




}
