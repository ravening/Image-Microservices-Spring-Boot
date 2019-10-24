package com.rakeshv.imageservice.controllers;

import java.util.Arrays;
import java.util.List;

import com.rakeshv.imageservice.models.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * HomeController
 */
@RestController
@Slf4j
@RequestMapping("/api/images")
public class HomeController {
    @Autowired
    private Environment environment;

    @GetMapping
    public List<Image> getImages() {
        List<Image> images = Arrays.asList(
            new Image(1L, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
            new Image(2L, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
            new Image(3L, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));

        return images;
    }

    @GetMapping("/{id}")
    public List<Image> getImageById(@PathVariable("id") Long id) {
        List<Image> imageList = Arrays.asList(new Image(id, "not found", "not found"));
        return imageList;
    }
    
}