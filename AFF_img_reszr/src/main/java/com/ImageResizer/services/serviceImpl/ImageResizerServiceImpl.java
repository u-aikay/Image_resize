package com.ImageResizer.services.serviceImpl;

import com.ImageResizer.services.ImageResizerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class ImageResizerServiceImpl implements ImageResizerService {

    private String imageFolder;

    @Override
    public boolean resizeImage(File sourceFile, int size) {
        try{

            BufferedImage originalImage = ImageIO.read(sourceFile);
            BufferedImage resizedImage = Scalr.resize(originalImage, size);

            String newFileName = FilenameUtils.getBaseName(sourceFile.getName())
                    + "_" + size + "."
                    + FilenameUtils.getExtension(sourceFile.getName());

            Path path = Paths.get(imageFolder, newFileName);
            File newImageFile = path.toFile();
            ImageIO.write(resizedImage, "jpg", newImageFile);

            //after resizing, for the original image to be replaced.
            originalImage.flush();

            return true;

        } catch (IOException e) {
            log.info(e.getMessage(), e);
            return false;
        }
    }
}
