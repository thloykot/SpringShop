package com.thl.spring.service.impl;

import com.thl.spring.dao.SneakersDao;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SneakersServiceImpl implements SneakersService {

    private final SneakersDao sneakersDao;

    @Override
    public int save(Sneakers sneakers) {
        log.info("Saving or updating sneakers");
        return Objects.requireNonNull(sneakersDao.save(
                sneakersDao.findIdByFirmAndModel(sneakers.getFirm(), sneakers.getModel())
                        .map(idOnly -> {
                            sneakers.setId(idOnly.getId());
                            return sneakers;
                        }).orElse(sneakers))).getId();
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
    public boolean delete(int id) {
        log.info("Deleting sneakers");
        return sneakersDao.deleteSneakersById(id) > 0;
    }
}
