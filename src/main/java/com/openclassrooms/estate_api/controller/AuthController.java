package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.User;
import com.openclassrooms.estate_api.model.dto.JwtDto;
import com.openclassrooms.estate_api.model.dto.UserRegisterDto;
import com.openclassrooms.estate_api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.email() == null || userRegisterDto.name() == null || userRegisterDto.password() == null) {
            return ResponseEntity.badRequest().body(new Object());
        }
        var jwt = userService.register(modelMapper.map(userRegisterDto, User.class));
        return ResponseEntity.ok(new JwtDto(jwt));
    }
}