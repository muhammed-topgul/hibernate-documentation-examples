package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * created by Muhammed Topgul on 21/09/2021 at 17:01
 */

@Entity(name = "Country")
@Table(schema = "associations", name = "city")
@Getter
@Setter
public class City extends BaseId {

    @NaturalId
    @Column(unique = true)
    private String code;

    private String name;

    @ManyToOne
    @JoinTable(schema = "associations", name = "country_city")
    private Country country;
}
