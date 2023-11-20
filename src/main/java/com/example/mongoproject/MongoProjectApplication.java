package com.example.mongoproject;

import com.example.mongoproject.init.ImageFactory;
import com.example.mongoproject.models.Artist;
import com.example.mongoproject.models.Image;
import com.example.mongoproject.repositories.ImageRepository;
import com.example.mongoproject.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {ImageRepository.class}, repositoryImplementationPostfix = "Impl")
public class MongoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoProjectApplication.class, args);
    }

    @Component
    public class Init implements CommandLineRunner {
        @Autowired
        private ImageService imageService;

        @Autowired
        private ImageFactory imageFactory;

        @Override
        public void run(String... args) throws Exception {
            List<Image> images = imageFactory.listBuilder().images(100, 100, image -> {
                Artist artist = imageFactory.makeArtist();
                image.setArtistInfo(artist);
                return image;
            });
            imageService.saveAllImage(images);
        }
    }

    @Component
    public class CustomCode implements CommandLineRunner {
        @Autowired
        private ImageService imageService;

        @Override
        public void run(String... args) throws Exception {
            List<Image> storedImages = imageService.getAllImage();
            for (Image i : storedImages) {
                System.out.println(i);
                imageService.deleteImage(i.getId()); // Удалить изображение и связанного художника
            }
        }
    }
}

