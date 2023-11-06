package com.example.mongoproject.repositories;

import com.example.mongoproject.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
    Optional<Image> getByArtist (String artist);
    List<Image> findByName(String name);
    List<Image> findByYearGreaterThan(int year);
    List<Image> findByGenre(String genre);
    List<Image> findByArtistAndYear(String artist, int year);
    List<Image> findAllArtistByGenre(String genre);
    List<Image> findAverageYearByArtist(String artist);
}
