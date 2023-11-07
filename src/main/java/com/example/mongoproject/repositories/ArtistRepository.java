package com.example.mongoproject.repositories;

import com.example.mongoproject.models.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, String> {
}
