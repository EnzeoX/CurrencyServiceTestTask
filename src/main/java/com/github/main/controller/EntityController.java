package com.github.main.controller;

import com.github.main.entity.EntityXml;
import com.github.main.service.IEntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class EntityController {

    private final IEntityService entityService;

    @GetMapping(value = "/getdata")
    public void getAllActualData() {
        entityService.getDataAndSave();
    }

    @GetMapping(value = "/date/{date}")
    public void getDataByDate(@PathVariable("date") String date) {
        List<EntityXml> listOfEntities = entityService.findByDate(date);
        if (listOfEntities.isEmpty()) {
            listOfEntities = entityService.findByDateFromEndpoint(date);
        }
        System.out.println(listOfEntities);
    }

    @GetMapping(value = "/currency/{currency}")
    public void getDataForCurrency(@PathVariable("currency") String currency) {
        List<EntityXml> listOfEntities = entityService.findByCurrency(currency);
        if (listOfEntities.isEmpty()) {
            listOfEntities = entityService.findByCurrencyFromEndpoint(currency);
        }
        System.out.println(listOfEntities);
    }

    @GetMapping(value = "/currency&date/{currency}&{date}")
    public void getDataForCurrencyAndDate(@PathVariable("currency") String currency, @PathVariable("date") String date) {
        List<EntityXml> entityXml = new ArrayList<>();
        try {
            entityXml.add(entityService.findByCurrencyAndDate(currency, date).orElseThrow());
        } catch (NoSuchElementException e) {
            entityXml = entityService.findByCurrencyAndDateFromEndpoint(currency, date);
        }
        System.out.println(entityXml);
    }
}
