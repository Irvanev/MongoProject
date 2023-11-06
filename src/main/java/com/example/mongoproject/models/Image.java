package com.example.mongoproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "images")
public class Image {
    @Id
    private String id;
    private String name;
    private String artist;
    private int year;
    private String genre;
    private int number_paintings;

    public Image(String name, String artist, int year, String genre, int number_paintings) {
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.number_paintings = number_paintings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumber_paintings() {
        return number_paintings;
    }

    public void setNumber_paintings(int number_paintings) {
        this.number_paintings = number_paintings;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", number_paintings=" + number_paintings +
                '}';
    }
}


