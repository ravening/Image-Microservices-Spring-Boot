package com.rakeshv.galleryservice.feign;

import com.rakeshv.galleryservice.models.Gallery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * GalleryServiceProxy
 */
@FeignClient(name = "image-service")
public interface GalleryServiceProxy {
    @GetMapping("/api/images/{id}")
    public Gallery getFeignGallery(@PathVariable Long id);
    
}