package com.thl.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name = "sneakers")
public class Sneakers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firm")
    private String firm;

    @Column(name = "size")
    private int size;

    @Column(name = "price")
    private int price;

    public Sneakers() {

    }

    public Sneakers(String firm, int size, int price) {
        this.firm = firm;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getFirm() {
        return firm;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Sneakers{" +
                "id=" + id +
                ", firm='" + firm + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
