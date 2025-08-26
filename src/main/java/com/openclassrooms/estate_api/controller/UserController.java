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
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserController implements UserApi {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostMapping(value = "/auth/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.email() == null || userRegisterDto.name() == null || userRegisterDto.password() == null) {
            return ResponseEntity.badRequest().body(new Object());
        }
        var jwt = userService.register(modelMapper.map(userRegisterDto, User.class));
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @Override
    @GetMapping(value = "/auth/me")
    public ResponseEntity<Object> me(Authentication authentication) {
        var user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    @Override
    @PostMapping(value = "/auth/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        var jwt = userService.login(userLoginDto.email(), userLoginDto.password());
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @Override
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Object> one(@PathVariable Integer id) {
        var user = userService.getUserById(id);
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }
}