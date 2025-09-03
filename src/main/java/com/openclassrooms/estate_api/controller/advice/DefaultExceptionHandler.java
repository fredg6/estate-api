package com.openclassrooms.estate_api.controller.advice;

import com.openclassrooms.estate_api.exception.UploadException;
import com.openclassrooms.estate_api.model.dto.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMessageDto> handleAuthenticationException(Exception ex) {
        ResponseMessageDto responseMessageDto = new ResponseMessageDto("Authentication failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMessageDto);
    }

    @ExceptionHandler(UploadException.class)
    public ResponseEntity<ResponseMessageDto> handleUploadException(Exception ex) {
        ResponseMessageDto responseMessageDto = new ResponseMessageDto(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessageDto);
    }
}