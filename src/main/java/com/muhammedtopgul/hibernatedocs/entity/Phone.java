package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.enumeration.PhoneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:15
 */

@Entity(name = "Phone")
@Getter
@Setter
public class Phone {

    @Id
    private Long id;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phone_type")
    private PhoneType type;
}
