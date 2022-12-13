package com.example.demovideosuperresolution.business

import com.example.demovideosuperresolution.VideoDisassemblyGrpcKt
import com.example.demovideosuperresolution.entities.Video
import com.example.demovideosuperresolution.mappers.Video_to_GrpcVideoMapper
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

import com.example.demovideosuperresolution.VideoDataStructures as Grpc

import kotlinx.coroutines.flow.flow
import java.io.OutputStream
import java.nio.ByteBuffer


class VideoDisassembly(
        @Autowired
        @Qualifier("disassemblyChannel")
        val channel: ManagedChannel
) {
    fun disassemble(video: Video): Flow<Grpc.Box> {
        val stub = VideoDisassemblyGrpcKt.VideoDisassemblyCoroutineStub(channel)
        val videoFlow = Video_to_GrpcVideoMapper.mapToGrpc(video)
        return stub.streamBoxes(videoFlow)
    }
}