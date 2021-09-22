package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 22/09/2021 at 22:32
 */

@Entity(name = "PhoneDetail")
@Table(schema = "associations", name = "phone_detail")
@Getter
@Setter
public class PhoneDetail extends BaseId {

    private String provider;

    private String technology;
}
