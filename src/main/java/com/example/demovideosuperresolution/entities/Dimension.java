package com.example.demovideosuperresolution.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dimension")
@Getter
@Setter
@ToString
public class Dimension {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;
}