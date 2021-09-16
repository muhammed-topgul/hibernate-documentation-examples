package com.muhammedtopgul.hibernatedocs.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * created by Muhammed Topgul on 16/09/2021 at 10:11
 */

@Entity(name = "DateEvent")
@Getter
@Setter
@ToString
public class DateEvent {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "`timestamp`")
    @Temporal(TemporalType.DATE)
    private Date timestamp;
}
