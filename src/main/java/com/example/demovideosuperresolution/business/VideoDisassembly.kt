package com.example.demovideosuperresolution.business

import com.example.demovideosuperresolution.entities.Video
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

import VideoFactory.Box
import VideoFactory.Video as RPCVideo

import kotlinx.coroutines.flow.flow
import java.io.OutputStream
import java.nio.ByteBuffer


class VideoDisassembly(
        @Autowired
        @Qualifier("disassemblyChannel")
        val channel: ManagedChannel
) {
    suspend fun disassemble(video: Video): Flow<Box> {

    }

    private suspend fun splitVideo(video: Video): Flow<RPCVideo> {
        val metadata = VideoFactory.VideoMetadata.newBuilder()
                .setName(video.name).build()


        return flow<RPCVideo> {
            emit(RPCVideo.newBuilder().setMetadata(metadata).build())

            for (startIdx in generateSequence(1, Int::inc).map { it * 32000 }) {
                val endIdx =
            }


        }


    }
}

private class FlushCallbackOutputStream<T>(
        val callback: suspend (T) -> Unit,
        val buffer: ByteBuffer)
    : OutputStream() {
    override fun write(b: Int) {
        TODO("Not yet implemented")
    }

    override fun flush() {
        callback
    }

}