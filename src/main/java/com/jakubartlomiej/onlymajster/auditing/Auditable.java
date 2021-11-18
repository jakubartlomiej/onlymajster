package com.jakubartlomiej.onlymajster.auditing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
    @CreatedBy
    @Column(updatable = false)
    protected Long createdBy;
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdDate;
    @LastModifiedBy
    protected Long lastModifiedBy;
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}