package com.thl.spring.model;

import com.thl.spring.dto.SneakersDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    public SneakersDto toDto() {
        return new SneakersDto(firm, size, price);
    }
}
