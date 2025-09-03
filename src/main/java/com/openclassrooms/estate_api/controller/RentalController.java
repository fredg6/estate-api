package com.openclassrooms.estate_api.controller;

import com.openclassrooms.estate_api.model.Rental;
import com.openclassrooms.estate_api.model.dto.*;
import com.openclassrooms.estate_api.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class RentalController implements RentalApi {
    private final ModelMapper modelMapper;
    private final RentalService rentalService;

    public RentalController(ModelMapper modelMapper, RentalService rentalService) {
        this.modelMapper = modelMapper;
        this.rentalService = rentalService;
    }

    @Override
    @PostMapping(value = "/rentals")
    public ResponseEntity<ResponseMessageDto> create(Authentication authentication, @ModelAttribute RentalCreationDto rentalCreationDto) {
        rentalService.create(rentalCreationDto, authentication.getName());
        return ResponseEntity.ok(new ResponseMessageDto("Rental created !"));
    }

    @Override
    @GetMapping(value = "/rentals")
    public ResponseEntity<RentalsDto> all() {
        var rentalList = rentalService.getAll();
        var rentalDtoList = rentalList.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new RentalsDto(rentalDtoList));
    }

    @Override
    @GetMapping(value = "/rentals/{id}")
    public ResponseEntity<RentalDto> one(@PathVariable Integer id) {
        var rental = rentalService.getById(id);
        return ResponseEntity.ok(toDto(rental));
    }

    @Override
    @PutMapping(value = "/rentals/{id}")
    public ResponseEntity<ResponseMessageDto> update(@PathVariable Integer id, @ModelAttribute RentalUpdateDto rentalUpdateDto) {
        rentalService.update(id, rentalUpdateDto);
        return ResponseEntity.ok(new ResponseMessageDto("Rental updated !"));
    }

    private RentalDto toDto(Rental rental) {
        var rentalDto = modelMapper.map(rental, RentalDto.class);
        rentalDto.setOwnerId(rental.getOwner().getId());
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd").withZone(ZoneId.systemDefault());
        rentalDto.setCreatedAt(dateTimeFormatter.format(rental.getCreatedAt()));
        rentalDto.setUpdatedAt(dateTimeFormatter.format(rental.getUpdatedAt()));
        return rentalDto;
    }
}