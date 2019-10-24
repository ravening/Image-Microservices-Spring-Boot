package com.rakeshv.galleryservice.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Gallery
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gallery {
    private Long id;
    private List<Object> images;
    
    public Gallery(Long galleryId) {
        this.id = galleryId;
    }
}