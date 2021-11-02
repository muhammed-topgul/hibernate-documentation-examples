package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul created at 27/09/2021 21:29
 */

@Entity(name = "Country")
@Table(schema = "collections", name = "country")
@Getter
@Setter
public class Country extends BaseId {

    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name")
    @Setter(value = AccessLevel.NONE)
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        this.cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city) {
        this.cities.remove(city);
        city.setCountry(null);
    }
}
