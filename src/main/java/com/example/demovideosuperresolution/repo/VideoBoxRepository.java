package com.example.demovideosuperresolution.repo;


import com.example.demovideosuperresolution.entities.VideoBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoBoxRepository extends JpaRepository<VideoBox, Long> {
    List<VideoBox> findAllByVideoName(String videoName);

    boolean existsByVideoName(String videoName);
}
