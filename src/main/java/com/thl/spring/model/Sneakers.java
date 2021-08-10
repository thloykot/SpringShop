package com.thl.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Entity
@Table
public class Sneakers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firm;

    private int size;

    private int price;

    public Sneakers(String firm, int size, int price) {
        this.firm = firm;
        this.size = size;
        this.price = price;
    }
}
