package com.muhammedtopgul.hibernatedocs.naturalid.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 29/09/2021 08:34
 */

@Entity(name = "Publisher")
@Table(schema = "natural_ids", name = "publisher")
@Getter
@Setter
public class Publisher extends BaseId {

    private String name;

}
