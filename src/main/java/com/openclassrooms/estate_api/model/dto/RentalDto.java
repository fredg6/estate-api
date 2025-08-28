package com.openclassrooms.estate_api.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RentalDto {
    private Integer id;
    private String name;
    private Long surface;
    private Long price;
    private String picture;
    private String description;
    private Integer ownerId;
    private String createdAt;
    private String updatedAt;
}