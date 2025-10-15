package umc.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.store.entity.Store;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "auth_code", nullable = false)
    private String authCode;

    @Column(name = "period", nullable = false)
    private Integer period;

    @Builder.Default
    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
