package com.snapp.linkshortener.shortenerservice.controller;

import com.snapp.linkshortener.shortenerservice.dto.ShortURLRequestDto;
import com.snapp.linkshortener.shortenerservice.dto.ShortURLResponseDto;
import com.snapp.linkshortener.shortenerservice.service.URLShortenerService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/url-shortener/")
public class URLShortenerController {

    private final URLShortenerService urlShortenerService;

    public URLShortenerController(URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<ShortURLResponseDto> generateShortURL(@RequestBody ShortURLRequestDto requestDto) {
        ShortURLResponseDto shortURLResponseDto = new ShortURLResponseDto();
        shortURLResponseDto = urlShortenerService.generateShortURL(requestDto);
        return new ResponseEntity<>(shortURLResponseDto, HttpStatus.CREATED);
    }
}
