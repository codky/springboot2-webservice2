package com.codky.book.springboot2webservice2.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * BaseTimeEntity 는 모든 Entity 의 상위 클래스가 되어 Entitiy 들의 createdDate, modifiedDate 를 자동으로 관리하는 역할을 한다.
 */
@Getter
@MappedSuperclass // JPA Entitiy 클래스들이 BaseTimeEntitiy 를 상속할 경우 필드들도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntitiy 클래스에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {

    @CreatedDate // Entitiy가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entitiy의 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
}
