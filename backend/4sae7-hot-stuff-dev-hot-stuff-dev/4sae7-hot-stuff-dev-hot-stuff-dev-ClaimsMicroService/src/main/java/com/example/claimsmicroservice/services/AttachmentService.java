package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.entities.Claim;
import com.example.claimsmicroservice.repositories.AttachmentRepository;
import com.example.claimsmicroservice.repositories.ClaimRepository;
import com.example.claimsmicroservice.responses.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentService implements IServiceAttachment{
    private AttachmentRepository attachmentRepository;
    private ClaimRepository claimRepository;

    @Override
    public List<Attachment> findByClaim(Long id) {
        Claim claim = claimRepository.findById(id).orElse(null);
        claim.setId(id);
        return attachmentRepository.findByClaim(claim);
    }
    private final Path root = Paths.get("uploads");
    public MessageResponse uploadFile(List<MultipartFile> files, Long id){
        Claim claim = claimRepository.findById(id).orElse(null);
        claim.setId(id);
        try {
            for (MultipartFile file: files) {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                Attachment attachment = new Attachment();
                attachment.setClaim(claim);
                attachment.setPath(file.getOriginalFilename());
                attachmentRepository.save(attachment);
            }
        }  catch (IOException e) {
            e.printStackTrace();
            return new MessageResponse(false, "Attention", "Operation not effectuated");
        }

        return  new MessageResponse(true, "Success", "Operation  effectuated");

    }

    @Override
    public Resource download(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());

        }
    }



}