package com.example.demovideosuperresolution.event;

import com.example.demovideosuperresolution.business.VideoAssembly;
import com.example.demovideosuperresolution.entities.Video;
import com.example.demovideosuperresolution.entities.VideoBox;
import com.example.demovideosuperresolution.mappers.VideoBox_to_GrpcMapper;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VideoAssemblyEvent {
    private final VideoAssembly assembler;

    @Autowired
    public VideoAssemblyEvent(VideoAssembly assembler) {
        this.assembler = assembler;
    }

    @Async
    public CompletableFuture<Video> assembleBoxes(List<VideoBox> boxEntities) {
        var boxList =
                boxEntities.stream().map(VideoBox_to_GrpcMapper.INSTANCE::mapToGrpc).toList();

        var video = GlobalScope.
    }
}
