package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.dto.RentalCreationDto;
import com.openclassrooms.estate_api.model.dto.ResponseDto;
import com.openclassrooms.estate_api.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class RentalController implements RentalApi {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Override
    @PostMapping(value = "/rentals")
    public ResponseEntity<ResponseDto> create(Authentication authentication, @ModelAttribute RentalCreationDto rentalCreationDto) {
        rentalService.create(rentalCreationDto, authentication.getName());
        return ResponseEntity.ok(new ResponseDto("Rental created !"));
    }
}