package com.idh.ded.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double weight; // lbs

    private String attributes;

    private String type;

    private Double cost; // gps

    @Lob
    private String description;

    private String notes;

    public Equipment(String name, Double weight, String attributes, String type, Double cost, String description, String notes) {
        this.name = name;
        this.weight = weight;
        this.attributes = attributes;
        this.type = type;
        this.cost = cost;
        this.description = description;
        this.notes = notes;
    }
}
