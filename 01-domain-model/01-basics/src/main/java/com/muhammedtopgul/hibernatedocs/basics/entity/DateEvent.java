package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.entity.base.BaseTimestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author muhammed-topgul created at 16/09/2021 10:11
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
