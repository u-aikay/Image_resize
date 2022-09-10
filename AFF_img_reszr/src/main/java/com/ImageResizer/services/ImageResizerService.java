package com.ImageResizer.services;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageResizerService {
    BufferedImage resizeImage(File sourceFile, int dimension);
}
