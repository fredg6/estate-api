package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication API")
@SecurityScheme(type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, name = "Authorization", scheme = "Bearer", bearerFormat = "JWT")
public interface AuthApi {
    @Operation(summary = "Création d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Création effectuée et utilisateur connecté", content = @Content(schema = @Schema(implementation = JwtDto.class))),
            @ApiResponse(responseCode = "400", description = "Donnée manquante")
    })
    ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto);

    @Operation(summary = "Récupération de l'utilisateur connecté", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur authentifié et récupéré", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Echec de l'authentification")
    })
    ResponseEntity<Object> me(Authentication authentication);

    @Operation(summary = "Connexion d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté", content = @Content(schema = @Schema(implementation = JwtDto.class))),
            @ApiResponse(responseCode = "401", description = "Echec de l'authentification", content = @Content(schema = @Schema(implementation = RestErrorDto.class)))
    })
    ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto);
}
