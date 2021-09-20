package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.entity.base.BaseTimestamp;
import com.muhammedtopgul.hibernatedocs.basics.enumeration.PhoneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:15
 */

@Entity(name = "Phone")
@Table(schema = "basics", name = "phone")
@Getter
@Setter
public class Phone extends BaseTimestamp {

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phone_type")
    private PhoneType type;
}
