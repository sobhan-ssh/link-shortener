package com.snapp.linkshortener.shortenerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortURLResponseDto {

    private String mainUrl;
    private String shortUrl;
}
