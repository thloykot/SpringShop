package com.thl.spring.redis.service.impl;

import com.thl.spring.redis.dao.impl.RedisDaoImpl;
import com.thl.spring.redis.model.RedisUser;
import com.thl.spring.redis.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisDaoImpl redisDao;

    @Override
    public void save(String username, RedisUser user) {
        redisDao.save(username, user);
    }

    @Override
    public Optional<RedisUser> find(String username) {
        return redisDao.findByUsername(username);
    }

    @Override
    public Date getUnlockDate(String username) {
        Calendar calendar = Calendar.getInstance();
        return redisDao.findByUsername(username).map(redisUser -> {
            calendar.setTime(redisUser.getDate());
            calendar.add(Calendar.HOUR_OF_DAY, 24);
            return calendar.getTime();
        }).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
