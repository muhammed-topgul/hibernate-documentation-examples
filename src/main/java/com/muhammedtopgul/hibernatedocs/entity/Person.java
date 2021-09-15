package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.converter.attributeConverter.GenderConverter;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Name;
import com.muhammedtopgul.hibernatedocs.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:25
 */

@Entity(name = "Person")
@Getter
@Setter
@ToString
public class Person {

    @Id
    private Integer id;

    private Name name;

    @Convert(converter = GenderConverter.class)
    public Gender gender;
}
