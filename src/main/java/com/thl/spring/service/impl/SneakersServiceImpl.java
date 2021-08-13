package com.thl.spring.service.impl;

import com.thl.spring.dao.SneakersDao;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class SneakersServiceImpl implements SneakersService {

    private final SneakersDao sneakersDao;

    @Override
    public Sneakers save(Sneakers sneakers) {
        log.info("Saving new sneakers {}", sneakers.getFirm());
        return sneakersDao.save(sneakers);
    }

    @Override
    public Optional<Sneakers> findById(int id) {
        log.info("Finding sneakers by id:{}", id);
        return sneakersDao.findById(id);
    }

    @Override
    public List<Sneakers> findByFirm(String firm) {
        List<Sneakers> sneakersList = sneakersDao.findSneakersByFirm(firm);
        log.info("{} sneakers of firm {} found", sneakersList.size(), firm);
        return sneakersList;
    }

    @Override
    public boolean isExists(Sneakers sneakers) {
        return sneakersDao.existsByFirmAndAndSizeAndPrice(
                sneakers.getFirm(),
                sneakers.getSize(),
                sneakers.getPrice()
        );
    }

    @Override
    public boolean isExistsById(int id) {
        return sneakersDao.existsById(id);
    }

    @Override
    public boolean isExistsByFirm(String firm) {
        return sneakersDao.existsByFirm(firm);
    }

    @Override
    public void delete(int id) {
        log.info("Deleting sneakers with id:{}", id);
        sneakersDao.deleteById(id);
    }
}
