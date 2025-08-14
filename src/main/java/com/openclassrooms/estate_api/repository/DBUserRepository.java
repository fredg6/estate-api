package com.openclassrooms.estate_api.repository;

import com.openclassrooms.estate_api.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
    DBUser findByUsername(String username);
}
