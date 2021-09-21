package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 21/09/2021 at 16:10
 */

@Entity(name = "Phone")
@Table(schema = "associations", name = "phone")
@Getter
@Setter
public class Phone extends BaseId {

    @Column(name = "`number`")
    private String number;
}
