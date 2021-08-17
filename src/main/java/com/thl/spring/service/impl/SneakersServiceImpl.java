package com.thl.spring.service.impl;

import com.thl.spring.dao.SneakersDao;
import com.thl.spring.dto.SneakersDto;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class SneakersServiceImpl implements SneakersService {

    private final SneakersDao sneakersDao;

    @Override
    public void save(SneakersDto sneakersDto) {
        Optional<Sneakers> sneakersOptional = find(sneakersDto);
        if (sneakersOptional.isPresent()) {
            log.info("Updating sneakers");
            sneakersDao.save(updateSneakers(sneakersOptional.get(), sneakersDto));
        } else {
            log.info("Saving new sneakers");
            sneakersDao.save(toSneakers(sneakersDto));
        }
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
        return sneakersDao.deleteSneakersById(id) != 0;
    }

    private Optional<Sneakers> find(SneakersDto sneakers) {
        return sneakersDao.findByFirmAndAndSizeAndPrice(
                sneakers.getFirm(),
                sneakers.getSize(),
                sneakers.getPrice());
    }

    private Sneakers toSneakers(SneakersDto sneakersDto) {
        Sneakers sneakers = new Sneakers();
        sneakers.setFirm(sneakersDto.getFirm());
        sneakers.setPrice(sneakersDto.getPrice());
        sneakers.setSize(sneakersDto.getSize());
        return sneakers;
    }

    private Sneakers updateSneakers(Sneakers sneakers, SneakersDto sneakersDto) {
        sneakers.setSize(sneakersDto.getSize());
        sneakers.setFirm(sneakersDto.getFirm());
        sneakers.setPrice(sneakersDto.getPrice());
        return sneakers;
    }
}
