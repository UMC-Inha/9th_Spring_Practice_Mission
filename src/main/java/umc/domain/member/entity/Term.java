package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.mapping.MemberTerm;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "term")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "is_required")
    private boolean is_required;
}
