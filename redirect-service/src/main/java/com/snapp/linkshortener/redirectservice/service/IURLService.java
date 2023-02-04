package com.snapp.linkshortener.redirectservice.service;


import com.snapp.linkshortener.redirectservice.dto.AnalyticalResponseDto;

public interface IURLService {

    String getOriginalUrl(String shortUrl);
    AnalyticalResponseDto getUrlClickRate(String shortUrl);

}
