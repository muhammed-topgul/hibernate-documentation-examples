package com.muhammedtopgul.hibernatedocs.persistencecontext.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 12/10/2021 at 14:16
 */

@Entity(name = "Person")
@Table(schema = "persistence_context", name = "person")
@Getter
@Setter
public class Person extends BaseId {

    private String name;
}
