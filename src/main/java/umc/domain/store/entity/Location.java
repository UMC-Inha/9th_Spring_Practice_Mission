package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "location")
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
