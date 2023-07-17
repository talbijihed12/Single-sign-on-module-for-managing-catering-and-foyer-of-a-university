package com.example.authentificationmicroservice.Repositories;

import com.example.authentificationmicroservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByNom(String nom);


    Boolean existsByNom(String nom);

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    long countByEnabled(boolean enabled);
}
