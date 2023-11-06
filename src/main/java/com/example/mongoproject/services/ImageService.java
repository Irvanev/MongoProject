package com.example.mongoproject.services;

import com.example.mongoproject.models.Image;

import java.util.List;
import java.util.Map;

public interface ImageService {
    Image createImage(Image image);
    Image getImage(String id);
    void deleteImage(String id);
    void deleteAllImage();
    void saveAllImage(List<Image> images);
    List<Image> getAllImage();
    List<Image> findByName(String name);
    Map<String, Double> findAverageYearByArtist();
    Image updateImage(String id, Image updatedImage);
}
