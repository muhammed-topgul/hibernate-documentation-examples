package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseTimestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * created by Muhammed Topgul on 16/09/2021 at 10:11
 */

@Entity(name = "DateEvent")
@Table(schema = "basics", name = "dateevent")
@Getter
@Setter
@ToString
public class DateEvent extends BaseTimestamp {

    @Column(name = "`timestamp`")
    @Temporal(TemporalType.DATE)
    private Date timestamp;
}
