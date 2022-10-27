package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.entity.Image;
import com.example.ecommerce_website.repository.ImageRepository;
import com.example.ecommerce_website.services.impl.StorageServiceImpl;
import com.example.ecommerce_website.services.interfaces.IImageService;
import com.example.ecommerce_website.services.interfaces.IProductService;
import com.example.ecommerce_website.services.interfaces.IStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private IStorageService storageService;
    @Autowired
    private IImageService imageService;
    @PostMapping("/upload/{productId}")
    public ResponseEntity<Image> uploadFile(@RequestParam(value = "file") MultipartFile file, @PathVariable int productId){
        String fileName = storageService.uploadFile(file);
        Image image = Image.builder().imageUrl(fileName).status("Active").build();
        Image imageSave = imageService.saveImage(image, productId);
        return new ResponseEntity<>(imageSave, HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok().contentLength(data.length)
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
