package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 22/09/2021 at 23:22
 */

@Entity(name = "Address")
@Table(schema = "associations", name = "address")
@Getter
@Setter
public class Address extends BaseId {

    private String street;

    @Column(name = "`number`")
    private String number;
}
