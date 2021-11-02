package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul created at 21/09/2021 16:59
 */

@Entity(name = "Country")
@Table(schema = "associations", name = "country")
@Getter
@Setter
public class Country extends BaseId {

    private String name;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        this.getCities().add(city);
        city.setCountry(this);
    }

    public void removeCity(City city) {
        this.getCities().remove(city);
        city.setCountry(null);
    }
}
