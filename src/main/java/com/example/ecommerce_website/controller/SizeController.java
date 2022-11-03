package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.request.SizeDtoRequest;
import com.example.ecommerce_website.services.interfaces.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping(path = "api")
public class SizeController {
    @Autowired
    private ISizeService sizeService;
    @PostMapping("/size")
    public ResponseEntity<SizeDtoRequest> saveSize(@Valid @RequestBody SizeDtoRequest size){
        SizeDtoRequest createdSize = sizeService.createNewSize(size);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSize);
    }

    @GetMapping("/sizes")
    public ResponseEntity<List<SizeDtoRequest>> getAllSize(){
        return ResponseEntity.ok().body(sizeService.getAllSize());
    }

    @DeleteMapping("/size/{sizeId}")
    public ResponseEntity<String> deleteASize(@PathVariable("sizeId") int id){
        sizeService.deleteASize(id);
        return ResponseEntity.ok().body("Delete size with id: "+ id+" successfully!");
    }

}
