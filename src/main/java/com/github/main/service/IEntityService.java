package com.github.main.service;

import com.github.main.entity.EntityXml;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEntityService {

    void getDataAndSave();

    void updateData();

    List<EntityXml> findByDate(String data);

    List<EntityXml> findByCurrency(String data);

    Optional<EntityXml> findByCurrencyAndDate(String currencyName, String date);

    List<EntityXml> findByDateFromEndpoint(String date);

    List<EntityXml> findByCurrencyFromEndpoint(String currencyName);

    List<EntityXml> findByCurrencyAndDateFromEndpoint(String currencyName, String date);

}
