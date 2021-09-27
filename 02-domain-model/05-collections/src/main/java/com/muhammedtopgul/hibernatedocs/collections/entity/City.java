package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 27/09/2021 at 21:30
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
}
