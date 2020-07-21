package com.dovganyuk.model;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;
}
