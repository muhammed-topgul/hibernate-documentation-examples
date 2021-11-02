package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul created at 27/09/2021 21:30
 */

@Entity(name = "City")
@Table(schema = "collections", name = "city")
@Getter
@Setter
public class City extends BaseId {

    @Column(name = "`code`", unique = true)
    @NaturalId
    private String code;

    private String name;

    @ManyToOne
    public Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "order_id")
    @Setter(value = AccessLevel.NONE)
    private List<District> districts = new ArrayList<>();

    public void addDistrict(District district) {
        this.districts.add(district);
        district.setCity(this);
    }

    public void removeDistrict(District district) {
        this.districts.remove(district);
        district.setCity(null);
    }
}
