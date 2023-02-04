package com.snapp.linkshortener.redirectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticalResponseDto {

    private String shortUrl;
    private Date createDate;
    private int clickRate;
}
