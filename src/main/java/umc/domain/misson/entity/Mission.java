package umc.domain.misson.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import umc.domain.store.entity.Store;

@Entity
@Table(name = "mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "points", nullable = false)
    private int points;

    @Column(name = "unique_number", nullable = false)
    private int unique_number;

    @Column(name = "end_date", nullable = false)
    private LocalDate end_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
