package com.github.main.repository;

import com.github.main.entity.EntityXml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEntityRepository extends JpaRepository<EntityXml, Long> {

    @Query(value = "SELECT * FROM CURRENCY_DATA WHERE CC=:name", nativeQuery = true)
    List<EntityXml> findByCurrencyName(@Param("name") String currencyName);

    @Query(value = "SELECT * FROM CURRENCY_DATA WHERE EXCHANGE_DATE=:date", nativeQuery = true)
    List<EntityXml> findByDate(@Param("date") String date);

    @Query(value = "SELECT * FROM CURRENCY_DATA WHERE CC=:name AND EXCHANGE_DATE=:date", nativeQuery = true)
    Optional<EntityXml> findByDateAndCurrency(@Param("name") String currencyName, @Param("date") String date);
}

