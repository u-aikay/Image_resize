package com.ImageResizer.controllers;

import com.ImageResizer.services.ImageResizerService;
import com.ImageResizer.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class ImageResizerController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ImageResizerService imageResizerService;

    @GetMapping("")
    public String uploadImage(){
        return "upload image";
    }

    @PostMapping("/resize")
    public String resizeImage(@RequestParam("image")MultipartFile imageFile, int dimension){

        File file = fileUploadService.uploadedImage(imageFile);
        imageResizerService.resizeImage(file, dimension);

        return "image resized successfully";
    }
}
