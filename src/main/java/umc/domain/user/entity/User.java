package umc.domain.user.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.user.enums.Gender;
import umc.domain.user.enums.MemberStatus;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity //이 클래스를 JPA 엔티티로 지정. JPA가 이 클래스를 보고 자동으로 SQL 테이블로 관리해준다.
@Getter //n모든 필드에 대해 자동으로 getter 메서드 생성
@Builder //빌더 패턴을 자동으로 만들어주는 Lombok 어노테이션. 객체를 생성할 때 생성자 대신 가독성 좋은 체이닝 형태로 만들 수 있음.
//보통 우리가 객체를 만들 때는 Member member = new Member("해원",25,~~); 이런식으로 한다.
//이건 편해보이지만 문제가 있다. 인자 순서를 헷갈리면 값이 잘못 들어간다던지, 생성자 파라미터가 많아지면 알아보기 힘들다던지,
//그래서 등장한게 빌더 패턴이다. 필더 패턴을 쓰면 명시적으로 어떤 값이 어떤 필드에 들어가는지가 보인다.
//@Builder를 사용하면 자동으로 Member.builder() 메서드와 내부 Builder 클래스를 생성해준다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)//매개변수 없는 생성자를 자동 생성, 다른 클래스에서는 new로 직접 생성하지 못하게 막음.
//즉, 외부에서 new Member()을 막고, JPA 내부에서만 사용할 수 있게 설계
@AllArgsConstructor(access = AccessLevel.PRIVATE)//모든 필드를 매개변수로 받는 생성자를 private로 생성. 외부에서 직접 호출 불가, 대신 @Builder가 내부적으로 이 생성자를 사용해 객체를 만듦.
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//생성 전략을 선택, IDENTITY인 경우, 순차적 생성 (1 → 2 → …)
    private Long id;

    @Column(name="name", length = 30, nullable = false)
    private String name;

    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name="point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name="email")
    private String email;

    @Column(name="phone_num")
    private String phoneNum;

    @Column(name="phone_verified")
    private boolean phoneVerified;

    @Column(name="inactive_time")
    private LocalDateTime inactiveTime;
}
