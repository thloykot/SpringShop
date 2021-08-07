package com.thl.spring.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@RequiredArgsConstructor()@NoArgsConstructor
@ToString
@Entity()
@Table(name = "sneakers")
public class Sneakers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(name = "firm", nullable = false)
    private String firm;

    @NonNull
    @Column(name = "size", nullable = false)
    private int size;

    @NonNull
    @Column(name = "price", nullable = false)
    private int price;
}
