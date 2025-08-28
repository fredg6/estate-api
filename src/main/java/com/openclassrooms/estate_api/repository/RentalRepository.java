package com.openclassrooms.estate_api.repository;

import com.openclassrooms.estate_api.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {}
