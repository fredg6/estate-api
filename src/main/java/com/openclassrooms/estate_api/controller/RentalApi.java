package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@Tag(name = "Rental API")
@SecurityScheme(name = "Authorization", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, scheme = "Bearer", bearerFormat = "JWT")
@ApiResponse(responseCode = "401", description = "Informations d'authentification invalides",
        content = @Content(
                schema = @Schema(implementation = ResponseMessageDto.class),
                examples = @ExampleObject(
                        value = """
                              {
                                "message": "Authentication failed"
                              }""")))
public interface RentalApi {
    @Operation(summary = "Création d'une offre de location", security = @SecurityRequirement(name = "Authorization"))
    @RequestBody(description = "Données de l'offre de location à créer",
            content = @Content(
                    mediaType = "multipart/form-data",
                    schema = @Schema(implementation = RentalCreationDto.class)))
    @ApiResponse(responseCode = "200", description = "Création effectuée",
            content = @Content(
                    schema = @Schema(implementation = ResponseMessageDto.class),
                    examples = @ExampleObject(
                            value = """
                              {
                                "message": "Rental created !"
                              }""")))
    ResponseEntity<ResponseMessageDto> create(Authentication authentication, @org.springframework.web.bind.annotation.RequestBody RentalCreationDto rentalCreationDto);

    @Operation(summary = "Récupération de toutes les offres de location", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponse(responseCode = "200", description = "Offres de location récupérées",
            content = @Content(
                    schema = @Schema(implementation = RentalsDto.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "rentals": [
                                        {
                                            "id": 1,
                                            "name": "test house 1",
                                            "surface": 432,
                                            "price": 300,
                                            "picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                                            "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.",
                                            "owner_id": 1,
                                            "created_at": "2012/12/02",
                                            "updated_at": "2014/12/02"
                                        },
                                        {
                                            "id": 2,
                                            "name": "test house 2",
                                            "surface": 154,
                                            "price": 200,
                                            "picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                                            "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.",
                                            "owner_id": 2,
                                            "created_at": "2012/12/02",
                                            "updated_at": "2014/12/02"
                                        },
                                        {
                                            "id": 3,
                                            "name": "test house 3",
                                            "surface": 234,
                                            "price": 100,
                                            "picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                                            "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.",
                                            "owner_id": 1,
                                            "created_at": "2012/12/02",
                                            "updated_at": "2014/12/02"
                                        }]
                                    }""")))
    ResponseEntity<RentalsDto> all();

    @Operation(summary = "Récupération d'une offre de location par son id", security = @SecurityRequirement(name = "Authorization"))
    @Parameter(name = "id", description = "Id de l'offre de location recherchée", example = "1")
    @ApiResponse(responseCode = "200", description = "Offre de location récupérée",
            content = @Content(
                    schema = @Schema(implementation = RentalDto.class),
                    examples = @ExampleObject(
                            value = """
                                {
                                    "id": 1,
                                    "name": "dream house",
                                    "surface": 24,
                                    "price": 30,
                                    "picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
                                    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.",
                                    "owner_id": 1,
                                    "created_at": "2012/12/02",
                                    "updated_at": "2014/12/02"
                                }""")))
    ResponseEntity<RentalDto> one(Integer id);

    @Operation(summary = "Mise à jour d'une offre de location", security = @SecurityRequirement(name = "Authorization"))
    @Parameters({
            @Parameter(name = "id", description = "Id de l'offre de location à mettre à jour", example = "1"),
            @Parameter(name = "rentalUpdateDto", description = "Données de l'offre de location à mettre à jour",
                    content = @Content(
                            schema = @Schema(implementation = RentalUpdateDto.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "name": "name",
                                          "surface": "50",
                                          "price": "200",
                                          "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat."
                                        }""")))})
    @ApiResponse(responseCode = "200", description = "Mise à jour effectuée",
            content = @Content(
                    schema = @Schema(implementation = ResponseMessageDto.class),
                    examples = @ExampleObject(
                            value = """
                              {
                                "message": "Rental updated !"
                              }""")))
    ResponseEntity<ResponseMessageDto> update(Integer id, RentalUpdateDto rentalUpdateDto);
}