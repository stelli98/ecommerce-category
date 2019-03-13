package com31.websiteecommerce.websiteecommerce.category.controller;

import com31.websiteecommerce.websiteecommerce.category.model.ApiKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiKeyErrorController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value=ApiKeyException.class)
    public String apikey(ApiKeyException exception){
        return "You are not authorized";
    }
}
