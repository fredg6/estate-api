package com.openclassrooms.estate_api.service;

import com.openclassrooms.estate_api.model.Rental;
import com.openclassrooms.estate_api.model.User;
import com.openclassrooms.estate_api.model.dto.RentalCreationDto;
import com.openclassrooms.estate_api.repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    private final ModelMapper modelMapper;
    private final FileSystemStorageService fileSystemStorageService;
    private final UserService userService;
    private final RentalRepository rentalRepository;

    public RentalService(ModelMapper modelMapper,
                         FileSystemStorageService fileSystemStorageService,
                         UserService userService,
                         RentalRepository rentalRepository) {
        this.modelMapper = modelMapper;
        this.fileSystemStorageService = fileSystemStorageService;
        this.userService = userService;
        this.rentalRepository = rentalRepository;
    }

    public Rental create(RentalCreationDto rentalCreationDto, String ownerEmail) {
        var pictureUrl = fileSystemStorageService.store(rentalCreationDto.picture());
        var owner = userService.getUserByEmail(ownerEmail);
        return rentalRepository.save(toEntity(rentalCreationDto, pictureUrl, owner));
    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    private Rental toEntity(RentalCreationDto rentalCreationDto, String pictureUrl, User owner) {
        var rental = modelMapper.map(rentalCreationDto, Rental.class);
        rental.setPicture(pictureUrl);
        rental.setOwner(owner);
        return rental;
    }
}