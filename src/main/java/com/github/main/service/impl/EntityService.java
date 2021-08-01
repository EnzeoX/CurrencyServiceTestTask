package com.github.main.service.impl;

import com.github.main.entity.EntityXml;
import com.github.main.repository.IEntityRepository;
import com.github.main.service.IEntityService;
import com.github.main.utils.XMLCustomDecoder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.github.main.utils.DateReverser.reverseDate;

@Component
@AllArgsConstructor
public class EntityService implements IEntityService {

    private static final XMLCustomDecoder decoder = new XMLCustomDecoder();

    private final IEntityRepository entityRepository;

    //---------- SAVES DATA FROM ENDPOINT TO DB ----------
    @Override
    public void getDataAndSave() {
        String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(URL, String.class);
        List<EntityXml> listOfEntities = decoder.getEntityFromXML(responseEntity.getBody());
        for (EntityXml xml : listOfEntities) {
            entityRepository.save(xml);
        }
    }

    //------------- UPDATE FOR ACTUAL DATA --------------
    @Override
    public void updateData() {
        //TODO AUTO UPDATE DATA FOR ACTUAL
    }

    //---------- FINDS DATA FROM DB BY DATE --------------
    @Override
    public List<EntityXml> findByDate(String date) {
        return entityRepository.findByDate(date);
    }

    //------- FINDS DATA FROM DB BY CURRENCY NAME -------
    @Override
    public List<EntityXml> findByCurrency(String currencyName) {
        return entityRepository.findByCurrencyName(currencyName);
    }

    //-- FINDS DATA FROM DB FOR DATE AND CURRENCY NAME ---
    @Override
    public Optional<EntityXml> findByCurrencyAndDate(String currencyName, String date) {
        return entityRepository.findByDateAndCurrency(currencyName, date);
    }

    //---------- FINDS DATA FROM ENDPOINT BY DATE --------------
    @Override
    public List<EntityXml> findByDateFromEndpoint(String date) {
        String URL = String.format("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=%s", reverseDate(date));
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(URL, String.class);
        return decoder.getEntityFromXML(responseEntity.getBody());
    }

    //------- FINDS DATA FROM ENDPOINT BY CURRENCY NAME -------
    @Override
    public List<EntityXml> findByCurrencyFromEndpoint(String currencyName) {
        String URL = String.format("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=%s", currencyName);
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(URL, String.class);
        return decoder.getEntityFromXML(responseEntity.getBody());
    }

    //-- FINDS DATA FROM ENDPOINT FOR DATE AND CURRENCY NAME ---
    @Override
    public List<EntityXml> findByCurrencyAndDateFromEndpoint(String currencyName, String date) {
        String URL = String.format("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=%s&date=%s", currencyName, reverseDate(date));
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(URL, String.class);
        List<EntityXml> entityXmls = decoder.getEntityFromXML(responseEntity.getBody());
        for (EntityXml entityXml : entityXmls) {
            entityRepository.save(entityXml);
        }
        return entityXmls;
    }
}
