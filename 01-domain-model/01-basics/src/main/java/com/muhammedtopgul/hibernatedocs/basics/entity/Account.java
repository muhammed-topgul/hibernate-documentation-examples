package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.enumeration.AccountType;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

/**
 * @author muhammed-topgul created at 16/09/2021 12:39
 */

@FilterDef(
        name = "activeAccount",
        parameters = @ParamDef(
                name = "active",
                type = "boolean"
        )
)
@Filter(
        name = "activeAccount",
        condition = "active_status = :active"
)
@Entity(name = "Account")
@Table(schema = "basics", name = "account")
// @Where(clause = "active = true")
@Getter
@Setter
@ToString
public class Account extends BaseId {

    private Double credit;

    private Double rate;

    @Formula(value = "credit * rate")
    @Setter(value = AccessLevel.NONE)
    private Double interest;

    @ManyToOne
    @JoinColumn(name = "account_client_id")
    private Client client;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    private Double amount;

    @Column(name = "active_status")
    private boolean active;

    private String description;
}
