package com.muhammedtopgul.hibernatedocs.immutability.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author muhammed-topgul created at 12/10/2021 11:38
 */

@Entity(name = "Event")
@Table(schema = "immutability", name = "event")
@Immutable
@Getter
@Setter
public class Event extends BaseId {
    private Date createdOn;
    private String message;
}
