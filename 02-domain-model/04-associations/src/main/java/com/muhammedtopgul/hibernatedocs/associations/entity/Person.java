package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 21/09/2021 at 16:02
 */

@Entity(name = "Person")
@Table(schema = "associations", name = "person")
@Getter
@Setter
public class Person extends BaseId {

    private String name;
}
