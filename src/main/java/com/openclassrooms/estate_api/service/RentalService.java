package com.openclassrooms.estate_api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.openclassrooms.estate_api.exception.UploadException;
import com.openclassrooms.estate_api.model.Rental;
import com.openclassrooms.estate_api.model.User;
import com.openclassrooms.estate_api.model.dto.RentalCreationDto;
import com.openclassrooms.estate_api.model.dto.RentalUpdateDto;
import com.openclassrooms.estate_api.repository.RentalRepository;
import jakarta.servlet.ServletContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RentalService {
    private final Path resourcesAbsolutePath;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;
    private final UserService userService;
    private final RentalRepository rentalRepository;

    public RentalService(ServletContext servletContext,
                         ModelMapper modelMapper,
                         Cloudinary cloudinary,
                         UserService userService,
                         RentalRepository rentalRepository) {
        this.resourcesAbsolutePath = Paths.get(servletContext.getContextPath(), "src/main/resources").toAbsolutePath();
        this.modelMapper = modelMapper;
        this.cloudinary = cloudinary;
        this.userService = userService;
        this.rentalRepository = rentalRepository;
    }

    public Rental create(RentalCreationDto rentalCreationDto, String ownerEmail) {
        var pictureUrl = upload(rentalCreationDto.picture());
        var owner = userService.getUserByEmail(ownerEmail);
        return rentalRepository.save(toEntity(rentalCreationDto, pictureUrl, owner));
    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(Integer id) {
        return rentalRepository.getReferenceById(id);
    }

    public Rental update(Integer id, RentalUpdateDto rentalUpdateDto) {
        var rentalToUpdate = rentalRepository.getReferenceById(id);
        modelMapper.map(rentalUpdateDto, rentalToUpdate);
        return rentalRepository.save(rentalToUpdate);
    }

    private String upload(MultipartFile file) {
        var params = ObjectUtils.asMap("use_filename", true, "unique_filename", false, "overwrite", true);
        var tempFile = new File(resourcesAbsolutePath + "/" + file.getOriginalFilename());
        try {
            file.transferTo(tempFile);
            var response = cloudinary.uploader().upload(tempFile, params);
            Files.delete(tempFile.toPath());
            return response.get("url").toString();
        } catch (IOException e) {
            throw new UploadException("Failed to upload file.", e);
        }
    }

    private Rental toEntity(RentalCreationDto rentalCreationDto, String pictureUrl, User owner) {
        var rental = modelMapper.map(rentalCreationDto, Rental.class);
        rental.setPicture(pictureUrl);
        rental.setOwner(owner);
        return rental;
    }
}