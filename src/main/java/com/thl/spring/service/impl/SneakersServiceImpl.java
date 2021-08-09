package com.thl.spring.service.impl;

import com.thl.spring.dao.SneakersDao;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor()
@Slf4j
public class SneakersServiceImpl implements SneakersService {

    private final SneakersDao sneakersDao;

    @Override
    public void save(Sneakers sneakers) {
        log.info("Saving new sneakers {}", sneakers.getFirm());
        sneakersDao.save(sneakers);
    }

    @Override
    public Sneakers findById(int id) {
        log.info("Finding sneakers by id:{}", id);
        return sneakersDao.findById(id).get();
    }

    @Override
    public List<Sneakers> findByName(String firm) {
        List<Sneakers> sneakersList = sneakersDao.findSneakersByFirm(firm);
        log.info("{} sneakers of firm {} found", sneakersList.size(), firm);
        return sneakersList;
    }

    @Override
    public void delete(int id) {
        log.info("Deleting sneakers with id:{}", id);
        sneakersDao.deleteById(id);
    }
}
