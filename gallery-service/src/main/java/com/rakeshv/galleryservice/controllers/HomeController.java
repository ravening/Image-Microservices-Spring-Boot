package com.rakeshv.galleryservice.controllers;

import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rakeshv.galleryservice.feign.GalleryServiceProxy;
import com.rakeshv.galleryservice.models.Gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * HomeController
 */
@RestController
@Slf4j
// @RequestMapping("/api/gallery")
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private GalleryServiceProxy galleryServiceProxy;

    @GetMapping("/")
    public String home() {
        return "Hello from Gallery Service running at port: " + environment.getProperty("local.server.port");
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/{id}")
    public Gallery getGallery(@PathVariable final Long id) {
        log.info("Getting the images");
        @SuppressWarnings("unchecked")
        List<Object> imagesList = restTemplate.getForObject("http://image-service/api/images/", List.class);
        log.info("Found list of images {}", imagesList);
        Gallery gallery = Gallery.builder()
                                .id(id)
                                .images(imagesList).build();
        
        log.info("returning images");
        return gallery;
    }

    public Gallery fallback(final Long id, Throwable hystrixCommand) {
        log.info("Falling back to fallback function");
        List<Object> image = restTemplate.getForObject("http://image-service/api/images/{id}", List.class, id);
        Gallery gallery = Gallery.builder()
                                .id(id)
                                .images(image).build();
        log.info("returning the default gallery");
        return gallery;
    }

    @RequestMapping("/admin")
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + environment.getProperty("local.server.port");
    }
    
    @GetMapping("/feign/{id}")
    public Gallery getFeignGallery (@PathVariable final Long id) {
        Gallery gallery = galleryServiceProxy.getFeignGallery(id);
        log.info("response from feign client is {}", gallery);

        return gallery;
    }
}