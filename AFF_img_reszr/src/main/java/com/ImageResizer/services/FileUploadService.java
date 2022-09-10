package com.ImageResizer.services;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;


public interface FileUploadService {
    File uploadedImage(MultipartFile img);
}
