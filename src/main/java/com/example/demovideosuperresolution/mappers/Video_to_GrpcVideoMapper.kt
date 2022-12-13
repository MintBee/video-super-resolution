package com.example.demovideosuperresolution.mappers

import com.example.demovideosuperresolution.VideoDataStructures
import com.example.demovideosuperresolution.VideoDataStructures.Video as GrpcVideo
import com.example.demovideosuperresolution.entities.Video
import com.example.demovideosuperresolution.video
import com.example.demovideosuperresolution.videoMetadata
import com.google.protobuf.kotlin.toByteString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object Video_to_GrpcVideoMapper {
    private const val bufferSize = 32_000

    fun mapToGrpc(videoEntity: Video): Flow<GrpcVideo> {
        val fullBufferCount = videoEntity.data.size / bufferSize
        val leftBytesCount = videoEntity.data.size % bufferSize

        return flow {
            val videoMetadata = video {
                metadata = videoMetadata {
                    name = videoEntity.name
                }
            }

            emit(videoMetadata)

            for (i in 0 until fullBufferCount) {
                val startIdx = i * bufferSize
                val videoData =
                    generateVideoInRange(videoEntity, startIdx until startIdx + bufferSize)
                emit(videoData)
            }

            val lastBufferStartIdx = fullBufferCount * bufferSize
            emit(
                generateVideoInRange(
                    videoEntity, lastBufferStartIdx until
                            lastBufferStartIdx + leftBytesCount
                )
            )
        }
    }

    private fun generateVideoInRange(
        videoEntity: Video,
        range: IntRange
    ) = video {
        part = videoEntity.data.slice(range)
            .toByteArray()
            .toByteString()
    }

    suspend fun mapToEntity(grpcVideoFlow: Flow<GrpcVideo>): Video {
        val newVideo = Video()
        val newDataArray = mutableListOf<Byte>()
        grpcVideoFlow.collect {
            when (it.dataCase) {
                VideoDataStructures.Video.DataCase.METADATA -> {
                    newVideo.name = it.metadata.name
                }

                VideoDataStructures.Video.DataCase.PART -> {
                    newDataArray.addAll(it.part)
                }

                else -> {}
            }
        }
        newVideo.data = newDataArray.toByteArray()
        return newVideo
    }


}