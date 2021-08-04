package com.thl.spring.controller;


import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SneakersController {

    @Autowired
    private SneakersService sneakersService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public void save(@RequestParam("firm") String firm,@RequestParam("size") int size, @RequestParam("price") int price) {
        Sneakers sneakers = new Sneakers(firm,size,price);
        sneakersService.save(sneakers);
    }

    @GetMapping(value = "/find")
    public Sneakers find(@RequestParam("id") int id) {
        return sneakersService.findById(id);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public void delete(@RequestParam("id") int id) {
        sneakersService.delete(id);
    }

}
