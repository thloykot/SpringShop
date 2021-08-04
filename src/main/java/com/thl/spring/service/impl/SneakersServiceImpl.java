package com.thl.spring.service.impl;

import com.thl.spring.dao.SneakersDao;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SneakersServiceImpl implements SneakersService {

    private final SneakersDao sneakersDao;

    public SneakersServiceImpl(SneakersDao sneakersDao) {
        this.sneakersDao = sneakersDao;
    }

    @Override
    public void save(Sneakers sneakers) {
        sneakersDao.save(sneakers);
    }

    @Override
    public Sneakers findById(int id) {
        return sneakersDao.findById(id).get();
    }

    @Override
    public void delete(int id) {
        sneakersDao.deleteById(id);
    }
}
