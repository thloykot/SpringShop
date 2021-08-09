package com.thl.spring.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity()
@Table()
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
