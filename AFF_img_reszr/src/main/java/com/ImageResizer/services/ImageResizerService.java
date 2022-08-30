package com.ImageResizer.services;

import java.io.File;

public interface ImageResizerService {
    boolean resizeImage(File sourceFile, int size);
}
