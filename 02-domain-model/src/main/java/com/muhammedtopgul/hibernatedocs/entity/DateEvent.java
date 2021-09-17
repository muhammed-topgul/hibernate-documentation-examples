package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseTimestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * created by Muhammed Topgul on 16/09/2021 at 10:11
 */

@Entity(name = "DateEvent")
@Getter
@Setter
@ToString
public class DateEvent extends BaseTimestamp {

    @Column(name = "`timestamp`")
    @Temporal(TemporalType.DATE)
    private Date timestamp;
}
