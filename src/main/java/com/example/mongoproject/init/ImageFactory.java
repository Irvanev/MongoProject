package com.example.mongoproject.init;

import com.example.mongoproject.models.Image;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ImageFactory {
    private static AtomicInteger nextId = new AtomicInteger(1);
    private final Faker faker = new Faker();

    private String name() {
        return faker.lorem().word();
    }

    private String artist() {
        return faker.artist().name();
    }

    private int year() {
        return faker.number().numberBetween(1800, 2023);
    }

    private String genre() {
        return faker.lorem().word();
    }

    private int number_of_paintings() {
        return faker.number().numberBetween(1, 130);
    }

    public List<String> artistInfo() {
        List<String> info = new ArrayList<>();
        // Генерируйте информацию о художнике, например, имя, национальность, стиль, биография и другое
        info.add(faker.artist().name());
        info.add(faker.artist().name());
        info.add(faker.lorem().sentence());
        // Добавьте другие атрибуты информации о художнике по мере необходимости
        return info;
    }

    public Image make(UnaryOperator<Image>... images) {
        final Image result = new Image(name(), artist(), year(), genre(), number_of_paintings());
        result.setArtistInfo(artistInfo()); // Устанавливаем информацию о художнике
        Stream.of(images).forEach(v -> v.apply(result));
        return result;
    }

    public static UnaryOperator<Image> oneUpId = s -> {
        s.setId(Integer.valueOf(nextId.getAndAdd(1)).toString());
        return s;
    };

    public ImageListDTOFactory listBuilder() {
        return new ImageListDTOFactory();
    }

    public class ImageListDTOFactory {
        public List<Image> images(int min, int max, UnaryOperator<Image>... images) {
            return IntStream.range(0, faker.number().numberBetween(min, max))
                    .mapToObj(i -> make(images))
                    .collect(Collectors.toList());
        }
    }
}

