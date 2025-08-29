package com.openclassrooms.estate_api.controller.advice;

import com.openclassrooms.estate_api.exception.UploadException;
import com.openclassrooms.estate_api.model.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> handleAuthenticationException(Exception ex) {
        ResponseDto responseDto = new ResponseDto("Authentication failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(UploadException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto> handleUploadException(Exception ex) {
        ResponseDto responseDto = new ResponseDto(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
}