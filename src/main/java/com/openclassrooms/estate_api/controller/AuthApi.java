package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.JwtDto;
import com.openclassrooms.estate_api.model.dto.UserRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication API")
public interface AuthApi {
    @Operation(summary = "Création d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Création effectuée", content = { @Content(schema = @Schema(implementation = JwtDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Donnée manquante")
    })
    ResponseEntity<Object> register(@RequestBody UserRegisterDto userRegisterDto);
}
