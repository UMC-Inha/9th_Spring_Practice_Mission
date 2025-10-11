package umc.domain.mission.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.mission.enums.Status;
import umc.domain.store.entity.Store;

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


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Integer due_date;

    @Column(nullable = false)
    private String discription;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
