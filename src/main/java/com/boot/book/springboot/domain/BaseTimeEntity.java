package com.boot.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
/*
    # MappedSuperClass
    * JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도
      칼럼으로 인식하도록 합니다.
 */
@MappedSuperclass
/*
    # EntityListeners(AuditingEntityListener.class)
    * BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
 */
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    /*
        # CreatedDate
        * Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.
     */
    @CreatedDate
    private LocalDateTime createdDate;

    /*
        # LastModifiedDate
        * 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
     */
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
