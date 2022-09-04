package com.ImageResizer;

import com.ImageResizer.services.ImageResizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class ImageResizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageResizerApplication.class, args);
    }

}
