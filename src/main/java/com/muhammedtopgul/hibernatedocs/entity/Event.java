package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.annotation.CustomCreationTimestamp;
import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * created by Muhammed Topgul on 16/09/2021 at 11:23
 */

@Entity(name = "Event")
@Getter
@Setter
public class Event extends BaseId {

    @Column(name = "`timestamp`")
    @CustomCreationTimestamp
    private Date timestamp;
}
