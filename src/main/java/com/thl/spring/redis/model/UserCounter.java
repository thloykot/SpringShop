package com.thl.spring.redis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UserCounter implements Serializable {

    private final int counter;
    private final long date;

}
