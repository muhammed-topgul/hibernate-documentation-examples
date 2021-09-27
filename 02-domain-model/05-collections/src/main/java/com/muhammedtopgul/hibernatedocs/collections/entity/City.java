package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 27/09/2021 at 21:30
 */

@Entity(name = "City")
@Table(schema = "collections", name = "city")
@Getter
@Setter
public class City extends BaseId {

    private String code;

    private String name;
}
