package com.example.demovideosuperresolution.event;

import com.example.demovideosuperresolution.entities.Video;
import com.example.demovideosuperresolution.entities.VideoBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VideoAssemblyEvent {
    private final VideoAssemblyService assemblyService;

    @Autowired
    public VideoAssemblyEvent(VideoAssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @Async
    public CompletableFuture<Video> assembleBoxes(List<VideoBox> boxes) {
        return assemblyService.assembly(boxes);
    }
}
