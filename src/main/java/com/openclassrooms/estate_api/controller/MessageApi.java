package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.MessageDto;
import com.openclassrooms.estate_api.model.dto.ResponseMessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    @RequestBody(description = "Message à envoyer, ids de l'expéditeur et de l'offre de location",
            content = @Content(
                    schema = @Schema(implementation = MessageDto.class),
                    examples = @ExampleObject(
                            value = """
                              {
                                "message": "mon message",
                                "user_id": 1,
                                "rental_id": 1
                              }""")))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message envoyé avec succès",
                    content = @Content(
                            schema = @Schema(implementation = ResponseMessageDto.class),
                            examples = @ExampleObject(
                                    value = """
                                          {
                                            "message": "Message sent with success"
                                          }"""))),
            @ApiResponse(responseCode = "401", description = "Informations d'authentification invalides",
                    content = @Content(
                            schema = @Schema(implementation = ResponseMessageDto.class),
                            examples = @ExampleObject(
                                    value = """
                                          {
                                            "message": "Authentication failed"
                                          }"""))),
            @ApiResponse(responseCode = "400", description = "Donnée manquante")})
    ResponseEntity<Object> send(MessageDto messageDto);
}