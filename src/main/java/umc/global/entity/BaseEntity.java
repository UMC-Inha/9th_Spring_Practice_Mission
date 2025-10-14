package umc.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //이 클래스는 실제 테이블로 매핑되진 않지만, 다른 엔티티들이 상속받아서 공통 컬럼을 함께 가지게 하겠다라는 뜻
//즉, 상속만 해도 하위 엔티티가 자동으로 created_at, updated_at컬럼을 가지게 됨.
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;
}
