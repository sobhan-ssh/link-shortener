package com.snapp.linkshortener.redirectservice.controller;

import com.snapp.linkshortener.redirectservice.dto.AnalyticalResponseDto;
import com.snapp.linkshortener.redirectservice.service.IURLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/url-shortener/")
public class URLShortenerController {

    private final IURLService urlService;

    public URLShortenerController(IURLService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl =  urlService.getOriginalUrl(shortUrl);
        return new ResponseEntity<>(originalUrl, HttpStatus.FOUND);
    }

    @GetMapping("/click-rate/{shortUrl}")
    public ResponseEntity<AnalyticalResponseDto> getUrlClickRate(@PathVariable String shortUrl) {
        AnalyticalResponseDto responseDto =  urlService.getUrlClickRate(shortUrl);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
