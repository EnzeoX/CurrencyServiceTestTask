package com.github.main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "currency_data")
@NoArgsConstructor
public class EntityXml {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String currencyCode;

    private String name;

    private String rate;

    private String cc;

    private String exchangeDate;

    public EntityXml(String currencyCode, String name, String rate, String cc, String exchangeDate) {
        this.currencyCode = currencyCode;
        this.name = name;
        this.rate = rate;
        this.cc = cc;
        this.exchangeDate = exchangeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityXml entityXml = (EntityXml) o;
        return Objects.equals(id, entityXml.id) && Objects.equals(name, entityXml.name) && Objects.equals(rate, entityXml.rate) && Objects.equals(cc, entityXml.cc) && Objects.equals(exchangeDate, entityXml.exchangeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rate, cc, exchangeDate);
    }

    @Override
    public String toString() {
        return "EntityXml{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", cc='" + cc + '\'' +
                ", exchangeDate='" + exchangeDate + '\'' +
                '}';
    }
}
