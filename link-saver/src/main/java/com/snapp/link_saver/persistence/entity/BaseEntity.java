package com.snapp.link_saver.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7697674550225582629L;

    public enum Status {
        ACTIVE, NOT_ACTIVE
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateTIme;

    @Column(name = "STATUS",length = 50)
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    public void prePersist() {
        setCreatedTime(new Date());
        setUpdateTIme(new Date());
        setStatus(Status.ACTIVE);
    }

    @PreUpdate
    public void preUpdate() {
        setUpdateTIme(new Date());
    }
}
