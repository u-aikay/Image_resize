package com.ImageResizer.imageUrlRepos;

import com.ImageResizer.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageUrlRepo extends JpaRepository<ImageEntity, Integer> {
}
