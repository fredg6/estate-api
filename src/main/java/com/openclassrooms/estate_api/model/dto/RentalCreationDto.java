package com.openclassrooms.estate_api.model.dto;

import org.springframework.web.multipart.MultipartFile;

public record RentalCreationDto(String name, String surface, String price, MultipartFile picture, String description) {}