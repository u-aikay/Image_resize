package com.ImageResizer.services.serviceImpl;

import com.ImageResizer.services.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${image.folder}")
    private String imgFolder;

    @Override
    public File uploadedImage(MultipartFile image) {
        try {
            Path path = Paths.get(imgFolder, image.getOriginalFilename());
            Files.write(path, image.getBytes());
            return path.toFile();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
