package com.javaWeb.lab07.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dashboards")
@Data
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private String owner;
}



