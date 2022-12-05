package com.example.demovideosuperresolution.business

import com.example.demovideosuperresolution.entities.Video

import VideoFactory.Box

import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
class VideoAssembly(
        @Autowired
        @Qualifier("assemblerChannel")
        val channel: ManagedChannel
){
    suspend fun assemble(boxFlow: Flow<Box>): Video {
        val stub = VideoAssemblyGrpcKt.VideoAssemblyCoroutineStub(channel)
        val grpcVideo = stub.assembleVideo(boxFlow)
        return collectVideo(grpcVideo)
    }

    private suspend fun collectVideo(videoParts: Flow<VideoFactory.Video>): Video {
        val videoPartsConcat = mutableListOf<Byte>()
        val video = Video()
        videoParts.map {
            when (it.dataCase) {
                VideoFactory.Video.DataCase.METADATA -> {
                    video.name = it.metadata.name
                }
                VideoFactory.Video.DataCase.PART -> {
                    videoPartsConcat.addAll(it.part)
                }
                VideoFactory.Video.DataCase.DATA_NOT_SET, null -> {
                }
            }
        }.collect()
        video.data = videoPartsConcat.toByteArray()
        return video
    }
}