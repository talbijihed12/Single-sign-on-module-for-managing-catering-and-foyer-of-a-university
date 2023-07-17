package com.example.claimsmicroservice.repositories;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    List<Attachment> findByClaim(Claim claim);

}
