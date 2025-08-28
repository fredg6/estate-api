package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.RentalCreationDto;
import com.openclassrooms.estate_api.model.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

@Tag(name = "Rental API")
@SecurityScheme(type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, name = "Authorization", scheme = "Bearer", bearerFormat = "JWT")
public interface RentalApi {
    @Operation(summary = "Création d'une offre de location", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Création effectuée", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "401", description = "Echec de l'authentification", useReturnTypeSchema = true)
    })
    ResponseEntity<ResponseDto> create(Authentication authentication, @ModelAttribute RentalCreationDto rentalCreationDto);
}