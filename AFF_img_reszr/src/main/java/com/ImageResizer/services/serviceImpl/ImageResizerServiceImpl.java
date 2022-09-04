package com.ImageResizer.services.serviceImpl;

import com.ImageResizer.services.ImageResizerService;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class ImageResizerServiceImpl implements ImageResizerService {

    @Value("${image.folder}")
    private String imageFolder;

    @Override
    public boolean resizeImage(File sourceFile, int dimension) {
        try{
            String extension = FilenameUtils.getExtension(sourceFile.getName());
            String newFileName = FilenameUtils.getBaseName(sourceFile.getName())
                    + "_" + dimension + "."
                    + extension;

            BufferedImage originalImage = ImageIO.read(sourceFile);
            BufferedImage resizedImage = Scalr.resize(originalImage, dimension);


            Path path = Paths.get(imageFolder, newFileName);

            File newImageFile = path.toFile();
            ImageIO.write(resizedImage, extension, newImageFile);

            /**
             * after resizing, for the original image to be replaced.
             */
            originalImage.flush();
            log.info("image successfully resized");

            return true;

        } catch (IOException e) {
            log.info(e.getMessage(), e);
            return false;
        }
    }
}