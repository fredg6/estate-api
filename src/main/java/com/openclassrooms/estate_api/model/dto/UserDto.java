package com.openclassrooms.estate_api.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd").withZone(ZoneId.systemDefault());

    private Integer id;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = DATE_TIME_FORMATTER.format(createdAt);
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = DATE_TIME_FORMATTER.format(updatedAt);
    }
}