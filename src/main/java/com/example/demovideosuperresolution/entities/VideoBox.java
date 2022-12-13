package com.example.demovideosuperresolution.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class VideoBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String videoName;

    @OneToOne
    @JoinColumn(name = "metadata_id", nullable = false)
    private BoxMetadata metadata;

    @Lob
    private byte[] data;

    public VideoBox() {

    }
}
