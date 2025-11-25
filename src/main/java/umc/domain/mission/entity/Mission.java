package umc.domain.mission.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.mission.enums.Status;
import umc.domain.store.entity.Store;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = true)
    private Integer price;

    @Column(nullable = false)
    private Integer point;


    //해당 미션 자체가 가계에서 진행 중인지 아닌지 판단
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String discription;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
