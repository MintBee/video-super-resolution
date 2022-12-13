package com.example.demovideosuperresolution.business

import com.example.demovideosuperresolution.entities.Video


import com.example.demovideosuperresolution.VideoAssemblyGrpcKt
import com.example.demovideosuperresolution.mappers.Video_to_GrpcVideoMapper
import com.example.demovideosuperresolution.VideoDataStructures as Grpc

import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.future.future
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture


@Component
class VideoAssembly(
        @Autowired
        @Qualifier("assemblerChannel")
        val channel: ManagedChannel
){
    suspend fun assemble(boxFlow: Flow<Grpc.Box>): Video {
        val stub = VideoAssemblyGrpcKt.VideoAssemblyCoroutineStub(channel)
        val grpcVideoFlow = stub.assembleVideo(boxFlow)
        return Video_to_GrpcVideoMapper.mapToEntity(grpcVideoFlow)
    }

    fun assemble(boxList: List<Grpc.Box>): CompletableFuture<Video> {
        return CoroutineScope(Dispatchers.IO).future {
            assemble(boxList.asFlow())
        }
    }
}