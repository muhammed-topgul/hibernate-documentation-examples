package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.Coordinates;
import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.GPS;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Target;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 16/09/2021 at 17:29
 */

@Entity(name = "City")
@Table(schema = "basics", name = "city")
@Getter
@Setter
public class City extends BaseId {

    private String name;

    @Embedded
    @Target(GPS.class)
    private Coordinates coordinates;
}
