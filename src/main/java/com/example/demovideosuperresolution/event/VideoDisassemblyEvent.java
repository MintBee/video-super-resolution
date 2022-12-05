package com.example.demovideosuperresolution.event;

import org.springframework.beans.factory.annotation.Autowired;

public class VideoDisassemblyEvent {
    VideoDisassemblyService videoDisassemblyService;

    @Autowired
    public VideoDisassemblyEvent(VideoDisassemblyService videoDisassemblyService) {
        this.videoDisassemblyService = videoDisassemblyService;
    }


}
