package com.example.claimsmicroservice.services;

import com.example.claimsmicroservice.entities.Attachment;
import com.example.claimsmicroservice.responses.MessageResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IServiceAttachment {

    List<Attachment> findByClaim(Long id);
    MessageResponse uploadFile(List<MultipartFile> files, Long id);
    Resource download(String filename) ;


}
