package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.annotation.CustomCreationTimestamp;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author muhammed-topgul created at 16/09/2021 11:23
 */

@Entity(name = "Event")
@Table(schema = "basics", name = "event")
@Getter
@Setter
public class Event extends BaseId {

    @Column(name = "`timestamp`")
    @CustomCreationTimestamp
    private Date timestamp;
}
