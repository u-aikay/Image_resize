package com.ImageResizer.services.serviceImpl;

import com.ImageResizer.entity.ImageEntity;
import com.ImageResizer.imageUrlRepos.ImageUrlRepo;
import com.ImageResizer.services.ImageResizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageResizerServiceImpl implements ImageResizerService {

    @Value("${image.folder}")
    private String imageFolder;
    @Autowired
    private final ImageUrlRepo imageUrlRepo;

    @Override
    public BufferedImage resizeImage(File sourceFile, int dimension) {
        try{

            String extension = FilenameUtils.getExtension(sourceFile.getName());
            String newFileName = FilenameUtils.getBaseName(sourceFile.getName())
                    + "_" + dimension + "."
                    + extension;

            //extracting the images from the folder/file
            BufferedImage originalImage = ImageIO.read(sourceFile);

            //using ImgScalr resize method to give a specific dimension to extracted image
            BufferedImage resizedImage = Scalr.resize(originalImage, dimension);

            //instantiating the path to save the edited image
            Path path = Paths.get(imageFolder, newFileName);

            //saving the edited image to the path
            File newImageFile = path.toFile();
            ImageIO.write(resizedImage, extension, newImageFile);

            /**
             * enable saving all resized image to a DB;
             * convert the resized image file to base64 and persist to imageEntity in DB
             */
            String base64ImgString = "";
            ByteArrayOutputStream byteArr = new ByteArrayOutputStream();

            try{
                ImageIO.write(resizedImage,extension,byteArr);
                byte[] imageBytes = byteArr.toByteArray();

                base64ImgString = Base64.getEncoder().encodeToString(imageBytes);
                byteArr.close();
            }catch (IOException e){
                e.printStackTrace();
            }

            ImageEntity img = ImageEntity.builder()
                    .img(base64ImgString)
                    .build();
            imageUrlRepo.save(img);

            originalImage.flush();
            log.info("image successfully resized");
            return resizedImage;

        } catch (IOException e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }
}