package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

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

    //코드 리뷰 추가 , 지역 정보
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "District", length = 100, nullable = false)
    private String district;

    @Column(name = "Street", length = 100, nullable = false)
    private String Street;

    @Column(name = "Latitude", length = 100, nullable = false)
    private String latitude;

    @Column(name = "Longitude", length = 100, nullable = false)
    private String Longitude;
}
