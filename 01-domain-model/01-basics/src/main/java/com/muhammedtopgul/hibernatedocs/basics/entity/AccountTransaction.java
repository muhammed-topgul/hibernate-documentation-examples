package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 20/09/2021 at 10:58
 */

@Entity(name = "AccountTransaction")
@Table(schema = "basics", name = "account_transaction")
@Getter
@Setter
public class AccountTransaction extends BaseId {

    @ManyToOne
    private Account account;

    private Integer cents;

    private String description;
}
