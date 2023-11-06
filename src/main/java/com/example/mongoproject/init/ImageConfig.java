package com.example.mongoproject.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfig {
    @Bean
    public ImageFactory imagesDtoFactory() {
        return new ImageFactory();
    }
}