package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.entity.base.BaseTimestamp;
import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.Name;
import com.muhammedtopgul.hibernatedocs.basics.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:25
 */

@Entity(name = "Person")
@Table(schema = "basics", name = "person")
@Getter
@Setter
@ToString
public class Person extends BaseTimestamp {

    private Name name;

    // @Convert(converter = GenderConverter.class)
    @Type(type = "genderType")
    public Gender gender;
}
