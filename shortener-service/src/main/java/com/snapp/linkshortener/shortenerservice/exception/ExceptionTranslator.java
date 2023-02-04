package com.snapp.linkshortener.shortenerservice.exception;

import com.snapp.linkshortener.shortenerservice.dto.DefaultResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_UNEXPECTED_ERROR = "unexpected error";
    public static final String URL_NOT_FOUND = "could not find url";
    public static final String INVALID_LINK = "invalid link";



    @ExceptionHandler(Exception.class)
    protected ResponseEntity<DefaultResponseDto> handleUncaughtException(Exception ex, WebRequest request) {
        DefaultResponseDto response = new DefaultResponseDto(new Date(), DEFAULT_UNEXPECTED_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<DefaultResponseDto> handleUncaughtException(RuntimeException ex, WebRequest request) {
        DefaultResponseDto response = new DefaultResponseDto(new Date(), DEFAULT_UNEXPECTED_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<DefaultResponseDto> handleUncaughtException(NotFoundException ex, WebRequest request) {
        DefaultResponseDto response = new DefaultResponseDto(new Date(), URL_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidException.class)
    protected ResponseEntity<DefaultResponseDto> handleUncaughtException(InvalidException ex, WebRequest request) {
        DefaultResponseDto response = new DefaultResponseDto(new Date(), INVALID_LINK);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

