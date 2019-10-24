package com.rakeshv.imageservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Image
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    private Long id;
    private String title;
    private String url;
    
}