package com.example.demovideosuperresolution.services;

import com.example.demovideosuperresolution.entities.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    Video getVideo(String name);

    void saveVideo(MultipartFile videoFile, String name) throws IOException;

    List<String> getAllVideoNames();
}
