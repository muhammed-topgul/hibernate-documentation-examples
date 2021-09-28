package com.muhammedtopgul.hibernatedocs.identifiers.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 20/09/2021 at 16:59
 */

@Entity(name = "SystemUser")
@Table(schema = "identifiers", name = "system_user")
@Getter
@Setter
public class SystemUser {

    @EmbeddedId
    private PK pk;

    private String name;
}
