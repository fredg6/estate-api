package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.MessageDto;
import com.openclassrooms.estate_api.model.dto.ResponseMessageDto;
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

@Tag(name = "Message API")
@SecurityScheme(type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, name = "Authorization", scheme = "Bearer", bearerFormat = "JWT")
public interface MessageApi {
    @Operation(summary = "Envoi d'un message au propriétaire d'une offre de location", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message envoyé avec succès", content = @Content(schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(responseCode = "401", description = "Echec de l'authentification", content = @Content(schema = @Schema(implementation = ResponseMessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Donnée manquante")
    })
    ResponseEntity<Object> send(MessageDto messageDto);
}