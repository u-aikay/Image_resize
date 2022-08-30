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
@RequestMapping("/")
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
    public String resizeImage(@RequestParam("image")MultipartFile imageFile, RedirectAttributes redirectAttributes,
                              int size){
        if(imageFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please choose file to upload.");
            return "redirect:/";
        }

        File file = fileUploadService.uploadedImage(imageFile);
        if(file == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Upload failed.");
            return "redirect:/";
        }

        boolean resizeResult = imageResizerService.resizeImage(file, size);
        if(!resizeResult) {
            redirectAttributes.addFlashAttribute("errorMessage", "Resize failed.");
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("successMessage", "File upload successfully.");
        return "redirect:/";
    }
}
