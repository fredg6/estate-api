package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.User;
import com.openclassrooms.estate_api.model.dto.JwtDto;
import com.openclassrooms.estate_api.model.dto.UserDto;
import com.openclassrooms.estate_api.model.dto.UserLoginDto;
import com.openclassrooms.estate_api.model.dto.UserRegisterDto;
import com.openclassrooms.estate_api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/auth", produces = APPLICATION_JSON_VALUE)
public class AuthController implements AuthApi {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.email() == null || userRegisterDto.name() == null || userRegisterDto.password() == null) {
            return ResponseEntity.badRequest().body(new Object());
        }
        var jwt = userService.register(modelMapper.map(userRegisterDto, User.class));
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @Override
    @GetMapping(value = "/me")
    public ResponseEntity<Object> me(Authentication authentication) {
        var user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

//    @Override
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        var jwt = userService.login(userLoginDto.login(), userLoginDto.password());
        return ResponseEntity.ok(new JwtDto(jwt));
    }
}