package com.snapp.link_saver.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOCATION")
public class URL extends BaseEntity {

    @NotNull
    @Column(name = "SHORT_LINK", length = 15)
    private String shortLink;

    @NotNull
    @Column(name = "MAIN_LINK", length = 200)
    private String mainLink;

    @NotNull
    @Column(name = "CLICK_COUNT")
    @ColumnDefault("'0'")
    private Integer clickCount;
}
