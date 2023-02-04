package com.snapp.linkshortener.redirectservice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "URL")
public class URL {


    @Id
    @NotNull
    @Column(name = "SHORT_LINK", length = 15)
    private String shortLink;

    @NotNull
    @Column(name = "ORIGINAL_LINK", length = 200)
    private String mainLink;

    @NotNull
    @Column(name = "CLICK_COUNT")
    @ColumnDefault("'0'")
    private Integer clickCount;

    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;
}
