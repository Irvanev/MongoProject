package com.example.mongoproject.services.impl;

import com.example.mongoproject.exceptions.ClientErrorException;
import com.example.mongoproject.models.Artist;
import com.example.mongoproject.models.Image;
import com.example.mongoproject.repositories.ArtistRepository;
import com.example.mongoproject.services.ImageService;
import com.example.mongoproject.repositories.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ArtistRepository artistRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ArtistRepository artistRepository) {
        this.imageRepository = imageRepository;
        this.artistRepository = artistRepository;
    }
    @Override
    public Image createImage(Image image) {
        imageRepository.save(image);
        return image;
    }
    @Override
    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }
    @Override
    public Image getImage(String id) {
        return imageRepository.findById(id)
                .orElseThrow(()->new
                        ClientErrorException.NotFoundException("Картина с id=[%s] не найдена", id));
    }
    @Override
    public List<Image> findByName(String name) {
        return imageRepository.findByName(name);
    }
    @Override
    public void deleteImage(String imageId) {
        Image image = imageRepository.findById(imageId).orElse(null);
        if (image != null) {
            Artist artist = image.getArtistInfo();
            if (artist != null) {
                artistRepository.delete(artist);
            }
            imageRepository.delete(image);
        }
    }
    @Override
    public void deleteAllImage() {
        imageRepository.deleteAll();
    }
    @Override
    public void saveAllImage(List<Image> images) {
        for (Image image : images) {
            Artist artist = image.getArtistInfo();
            artistRepository.save(artist);
            imageRepository.save(image);
        }
    }


    @Override
    public Map<String, Double> findAverageYearByArtist() {
        List<Image> images = imageRepository.findAll();

        Map<String, Double> averageYearsByArtist = images.stream()
                .collect(Collectors.groupingBy(Image::getArtist,
                        Collectors.averagingInt(Image::getYear)));
        return averageYearsByArtist;
    }
    @Override
    public Image updateImage(String id, Image updatedImage) {
        Image existingImage = imageRepository.findById(id)
                .orElseThrow(() -> new ClientErrorException("Image not found with id " + id) {
                });
        existingImage.setName(updatedImage.getName());
        existingImage.setArtist(updatedImage.getArtist());
        existingImage.setYear(updatedImage.getYear());
        existingImage.setGenre(updatedImage.getGenre());
        existingImage.setNumber_paintings(updatedImage.getNumber_paintings());

        // Сохранение обновленного изображения
        Image savedImage = imageRepository.save(existingImage);

        return savedImage;
    }
}
