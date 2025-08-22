package com.openclassrooms.estate_api.controller.advice;

import com.openclassrooms.estate_api.model.dto.RestErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<RestErrorDto> handleAuthenticationException(Exception ex) {
        RestErrorDto restErrorDto = new RestErrorDto("Authentication failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restErrorDto);
    }
}