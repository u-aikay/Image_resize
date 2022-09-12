//package com.ImageResizer.services.serviceImpl;
//
//import com.ImageResizer.imageUrlRepos.ImageUrlRepo;
//import com.ImageResizer.services.CloudinaryService;
//import com.cloudinary.Cloudinary;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Map;
//
//@Component
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class CloudinaryServiceImpl implements CloudinaryService {
//    @Autowired
//    private Cloudinary cloudinaryInstance;
//    private final ImageUrlRepo imageUrlRepo;
//
//    @Override
//    public String uploadFile(MultipartFile image) {
//        try {
//            File uploadedFile = convertMultiPartToFile(image);
//            Map uploadResult = cloudinaryInstance.uploader().upload(uploadedFile, com.cloudinary.utils.ObjectUtils.emptyMap());
//            boolean isDeleted = uploadedFile.delete();
//
//            if (isDeleted){
//                log.info("original upload deleted");
//            }
//            return uploadResult.get("url").toString();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//
//        }
//    }
//
//
//    private File convertMultiPartToFile(MultipartFile image) throws IOException {
//        String file =  image.getOriginalFilename();
//
//        if (file == null) throw new AssertionError();
//        File convFile = new File(file);
//        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
//        fileOutputStream.write(image.getBytes());
//        fileOutputStream.close();
//        return convFile;
//
//    }
//}
