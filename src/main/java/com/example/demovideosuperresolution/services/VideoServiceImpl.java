package com.example.demovideosuperresolution.services;

import com.example.demovideosuperresolution.entities.Video;
import com.example.demovideosuperresolution.exceptions.VideoAlreadyExistsException;
import com.example.demovideosuperresolution.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video getVideo(String name) {
        if (videoRepository.existsByName(name)) {
            return videoRepository.findByName(name);
        } else {
            return null;
        }
    }
    @Override
    public void saveVideo(MultipartFile videoFile, String name) throws IOException {
        if (videoRepository.existsByName(name)) {
            throw new VideoAlreadyExistsException();
        }

        Video newVideo = new Video(name, videoFile.getBytes());
        videoRepository.save(newVideo);
    }

    @Override
    public List<String> getAllVideoNames() {
        return videoRepository.getAllNames();
    }
}
