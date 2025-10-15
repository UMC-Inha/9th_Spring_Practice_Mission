package umc.domain.region.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",  nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "region")
    private List<Member> members = new ArrayList<>();
}
