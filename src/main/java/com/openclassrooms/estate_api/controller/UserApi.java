package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.security.core.Authentication;

@Tag(name = "User API")
@SecurityScheme(type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, name = "Authorization", scheme = "Bearer", bearerFormat = "JWT")
public interface UserApi {
    @Operation(summary = "Création d'un utilisateur")
    @RequestBody(description = "Données de l'utilisateur à créer",
               content = @Content(
                    schema = @Schema(implementation = UserRegisterDto.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "email": "test@test.com",
                                    "name": "test TEST",
                                    "password": "test!31"
                                }""")))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Création effectuée et utilisateur connecté",
                    content = @Content(
                            schema = @Schema(implementation = JwtDto.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "token": "jwt"
                                        }"""))),
            @ApiResponse(responseCode = "400", description = "Donnée manquante")})
    ResponseEntity<Object> register(UserRegisterDto userRegisterDto);

    @Operation(summary = "Récupération de l'utilisateur connecté", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "id": 1,
                                          "name": "Test TEST",
                                          "email": "test@test.com",
                                          "created_at": "2022/02/02",
                                          "updated_at": "2022/08/02"
                                        }"""))),
            @ApiResponse(responseCode = "401", description = "Informations d'authentification invalides",
                    content = @Content(
                            schema = @Schema(implementation = ResponseMessageDto.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "message": "Authentication failed"
                                        }""")))})
    ResponseEntity<UserDto> me(Authentication authentication);

    @Operation(summary = "Connexion d'un utilisateur")
    @RequestBody(description = "Données de l'utilisateur à connecter",
            content = @Content(
                    schema = @Schema(implementation = UserLoginDto.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "email": "test@test.com",
                                        "password": "test!31"
                                    }""")))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur connecté",
                    content = @Content(
                            schema = @Schema(implementation = JwtDto.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "token": "jwt"
                                        }"""))),
            @ApiResponse(responseCode = "401", description = "Informations d'authentification invalides",
                    content = @Content(
                            schema = @Schema(implementation = ResponseMessageDto.class),
                            examples = @ExampleObject(
                                    value = """
                              {
                                "message": "Authentication failed"
                              }""")))})
    ResponseEntity<JwtDto> login(UserLoginDto userLoginDto);

    @Operation(summary = "Récupération d'un utilisateur par son id", security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "id", description = "Id de l'utilisateur recherché", example = "2")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "id": 2,
                                            	"name": "Owner Name",
                                            	"email": "test@test.com",
                                            	"created_at": "2022/02/02",
                                            	"updated_at": "2022/08/02"
                                            }"""))),
            @ApiResponse(responseCode = "401", description = "Informations d'authentification invalides",
                    content = @Content(
                            schema = @Schema(implementation = ResponseMessageDto.class),
                            examples = @ExampleObject(
                                    value = """
                                          {
                                            "message": "Authentication failed"
                                          }""")))
    })
    ResponseEntity<UserDto> one(Integer id);
}