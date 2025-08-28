package com.openclassrooms.estate_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "rentals")
@Data
@EqualsAndHashCode(callSuper = true)
public class Rental extends BaseEntity {
    private String name;
    private Long surface;
    private Long price;
    private String picture;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
}