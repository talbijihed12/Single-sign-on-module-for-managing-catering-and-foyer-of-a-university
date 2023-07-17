package com.example.authentificationmicroservice.Repositories;

import com.example.authentificationmicroservice.Entity.Role;
import com.example.authentificationmicroservice.Entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);

}
