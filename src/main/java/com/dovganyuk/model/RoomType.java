package com.dovganyuk.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "room_type")
public class RoomType {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;
}
