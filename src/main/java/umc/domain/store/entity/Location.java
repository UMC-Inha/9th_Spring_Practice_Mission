package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.mission.entity.mapping.MemberMission;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name",nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "location")
    private List<Store>  stores =  new ArrayList<>();


}
