package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 27/09/2021 21:52
 */

@Entity(name = "District")
@Table(schema = "collections", name = "district")
@Getter
@Setter
public class District extends BaseId {

    private String name;

    @ManyToOne
    private City city;
}
