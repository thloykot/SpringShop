package com.thl.spring.redis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Setter
@Getter
public class RedisUserCounter implements Serializable {

    private final int counter;
    private final ZonedDateTime date;

}
