package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;

/**
 * created by Muhammed Topgul on 16/09/2021 at 12:39
 */

@Entity(name = "Account")
@Getter
@Setter
@ToString
public class Account extends BaseId {

    private Double credit;

    private Double rate;

    @Formula(value = "credit * rate")
    @Setter(value = AccessLevel.NONE)
    private Double interest;
}
