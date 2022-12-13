package com.example.demovideosuperresolution.business

import com.example.demovideosuperresolution.BoxSuperResolutionGrpcKt
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

import com.example.demovideosuperresolution.VideoDataStructures as Grpc

@Component
class BoxesUpscaling(
    @Autowired
    @Qualifier("boxUpscalingChannel")
    val channel: ManagedChannel
) {
    fun upscale(boxFlow: Flow<Grpc.Box>): Flow<Grpc.Box> {
        val stub = BoxSuperResolutionGrpcKt.BoxSuperResolutionCoroutineStub(channel)
        return stub.inferBoxes(boxFlow)
    }
}