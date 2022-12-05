package com.example.demovideosuperresolution.controllers;


import com.example.demovideosuperresolution.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    @GetMapping("/names")
    ResponseEntity<List<String>> getAllVideoNames() {
        return ResponseEntity.ok(videoService.getAllVideoNames());
    }

    @GetMapping("/{name}")
    ResponseEntity<Resource> getVideoByName(@PathVariable("name") String name) {
        return ResponseEntity
                .ok(new ByteArrayResource(videoService.getVideo(name).getData()));
    }

    @PostMapping()
    public ResponseEntity<String> saveVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name) throws IOException {
        videoService.saveVideo(file, name);
        return ResponseEntity.ok("Video saved successfully");
    }
}
