package com.example.demovideosuperresolution.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoxMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long frameOrder;

    @OneToOne(optional = false)
    @JoinColumn(name = "position_ID", nullable = false)
    private Position position;

    @OneToOne(optional = false)
    @JoinColumn(name = "dimension_ID", nullable = false)
    private Dimension dimension;
}
