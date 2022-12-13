package com.example.demovideosuperresolution.mappers

import com.example.demovideosuperresolution.box
import com.example.demovideosuperresolution.boxMetadata
import com.example.demovideosuperresolution.dimension
import com.example.demovideosuperresolution.entities.BoxMetadata
import com.example.demovideosuperresolution.entities.Dimension
import com.example.demovideosuperresolution.entities.Position
import com.example.demovideosuperresolution.entities.VideoBox
import com.example.demovideosuperresolution.position
import com.google.protobuf.kotlin.toByteString
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

import com.example.demovideosuperresolution.VideoDataStructures as Grpc



object VideoBox_to_GrpcMapper {

    fun mapToGrpc(videoBoxEntity: VideoBox): Grpc.Box {
        return box {
            val entityPosition = videoBoxEntity.metadata.position
            val entityDimension = videoBoxEntity.metadata.dimension
            metadata = boxMetadata {
                videoName = videoBoxEntity.videoName
                frameOrder = videoBoxEntity.metadata.frameOrder
                position = position {
                    x = entityPosition.x
                    y = entityPosition.y
                }
                dimension = dimension {
                    height = entityDimension.height
                    width = entityDimension.width
                }
            }
            data = videoBoxEntity.data.toByteString()
        }
    }

    fun mapToEntity(grpcVideo: Grpc.Box): VideoBox {
        val newVideoBox = VideoBox()
        newVideoBox.videoName = grpcVideo.metadata.videoName
        newVideoBox.metadata = BoxMetadata()
        newVideoBox.metadata.frameOrder = grpcVideo.metadata.frameOrder
        newVideoBox.metadata.position = PositionMapper.instance.toEntity(grpcVideo.metadata.position)
        newVideoBox.metadata.dimension = DimensionMapper.instance.toEntity(grpcVideo.metadata.dimension)
        newVideoBox.data = grpcVideo.data.toByteArray()
        return newVideoBox
    }
}


@Mapper
private interface PositionMapper {

    fun toGrpc(positionEntity: Position): Grpc.Position {
        return position {
            x = positionEntity.x
            y = positionEntity.y
        }
    }

    @Mapping(target = "id", ignore = true)
    fun toEntity(grpcPosition: Grpc.Position): Position

    companion object {
        @JvmStatic
        val instance: PositionMapper = Mappers.getMapper(PositionMapper::class.java)
    }
}

@Mapper
private interface DimensionMapper {

    fun toGrpc(dimensionEntity: Dimension): Grpc.Dimension {
        return dimension {
            height = dimensionEntity.height
            width = dimensionEntity.width
        }
    }

    @Mapping(target = "id", ignore = true)
    fun toEntity(grpcDimension: Grpc.Dimension): Dimension

    companion object {
        @JvmStatic
        val instance: DimensionMapper = Mappers.getMapper(DimensionMapper::class.java)
    }
}