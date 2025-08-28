package com.openclassrooms.estate_api.service;

import com.openclassrooms.estate_api.model.Rental;
import com.openclassrooms.estate_api.model.User;
import com.openclassrooms.estate_api.model.dto.RentalCreationDto;
import com.openclassrooms.estate_api.repository.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    private static final String RENTAL_PICTURE_DIR_PATH_ON_CLASSPATH = "/images/rental/";
    private final int serverPort;
    private final ModelMapper modelMapper;
    private final StorageService storageService;
    private final UserService userService;
    private final RentalRepository rentalRepository;

    public RentalService(@Value("${server.port}") int serverPort,
                         ModelMapper modelMapper,
                         StorageService storageService,
                         UserService userService,
                         RentalRepository rentalRepository) {
        this.serverPort = serverPort;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
        this.userService = userService;
        this.rentalRepository = rentalRepository;
    }

    public Rental create(RentalCreationDto rentalCreationDto, String ownerEmail) {
        var pictureFilename = storageService.store(rentalCreationDto.picture());
        var pictureUrl = buildPictureUrl(pictureFilename);
        var owner = userService.getUserByEmail(ownerEmail);
        return rentalRepository.save(toEntity(rentalCreationDto, pictureUrl, owner));
    }

    private String buildPictureUrl(String pictureFilename) {
        return "http://localhost:" +
                serverPort +
                RENTAL_PICTURE_DIR_PATH_ON_CLASSPATH +
                pictureFilename;
    }

    private Rental toEntity(RentalCreationDto rentalCreationDto, String pictureUrl, User owner) {
        var rental = modelMapper.map(rentalCreationDto, Rental.class);
        rental.setPicture(pictureUrl);
        rental.setOwner(owner);
        return rental;
    }
}