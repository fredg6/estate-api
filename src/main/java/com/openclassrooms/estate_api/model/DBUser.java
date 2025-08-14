package com.openclassrooms.estate_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
public class DBUser extends BaseEntity {
    @Column(name = "email")
    private String username;
    private String password;
    private String name;
}