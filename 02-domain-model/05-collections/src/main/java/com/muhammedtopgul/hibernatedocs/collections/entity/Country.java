package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Muhammed Topgul on 27/09/2021 at 21:29
 */

@Entity(name = "Country")
@Table(schema = "collections", name = "country")
@Getter
@Setter
public class Country extends BaseId {

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "collections", name = "country_city")
    @Setter(value = AccessLevel.NONE)
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        this.cities.add(city);
    }
}
