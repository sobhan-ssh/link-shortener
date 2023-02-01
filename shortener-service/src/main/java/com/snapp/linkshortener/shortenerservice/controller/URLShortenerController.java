package com.snapp.linkshortener.shortenerservice.controller;

import com.snapp.linkshortener.shortenerservice.dto.ShortURLRequestDto;
import com.snapp.linkshortener.shortenerservice.dto.ShortURLResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
public class URLShortenerController {

    @PostMapping
    public ResponseEntity<ShortURLResponseDto> createLocation(@RequestBody ShortURLRequestDto requestDto) {
        ShortURLResponseDto shortURLResponseDto = new ShortURLResponseDto();
        shortURLResponseDto.setShortUrl(UUID.randomUUID().toString());
        //write on redis1, then if someone looks for it before it gets save on db, we will read it from redis
        //convert and send on kafka
        return new ResponseEntity<>(shortURLResponseDto, HttpStatus.CREATED);
    }
}
