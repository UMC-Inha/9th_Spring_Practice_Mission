package umc.domain.store.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.enums.Category;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 20)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column
    private Integer rate;
}
