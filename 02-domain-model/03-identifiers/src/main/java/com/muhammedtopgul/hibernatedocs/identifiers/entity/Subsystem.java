package com.muhammedtopgul.hibernatedocs.identifiers.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 20/09/2021 at 17:07
 */

@Entity(name = "Subsystem")
@Table(schema = "identifiers", name = "subsystem")
@Getter
@Setter
public class Subsystem extends BaseId {

    private String description;
}
