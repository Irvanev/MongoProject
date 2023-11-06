package com.example.mongoproject.controllers;

import com.example.mongoproject.models.Image;
import com.example.mongoproject.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/images")
    Iterable<Image> all() {
        return imageService.getAllImage();
    }

    @GetMapping("/images/findByName")
    Iterable<Image> findByName(@RequestParam String name) {
        return imageService.findByName(name);
    }

    @DeleteMapping("/images/{id}")
    void delete(@PathVariable String id) {
        imageService.deleteImage(id);
    }
    @PostMapping("/images")
    Image create(@RequestBody Image image) {
        return imageService.createImage(image);
    }
    @GetMapping("/images/average-year")
    public Map<String, Double> getAverageYearByArtist() {
        return imageService.findAverageYearByArtist();
    }
    @PutMapping("images/{id}")
    public Image updateImage(@PathVariable String id, @RequestBody Image updatedImage) {
        return imageService.updateImage(id, updatedImage);
    }
}
