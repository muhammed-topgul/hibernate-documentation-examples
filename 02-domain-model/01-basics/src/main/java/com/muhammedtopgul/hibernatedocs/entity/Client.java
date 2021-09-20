package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Muhammed Topgul on 16/09/2021 at 12:48
 */

@Entity(name = "Client")
@Getter
@Setter
@ToString
public class Client extends BaseId {

    private String firstName;

    private String lastName;

    @Where(clause = "account_type = 'DEBIT'")
    @OneToMany(mappedBy = "client")
    @Setter(value = AccessLevel.NONE)
    private List<Account> debitAccounts = new ArrayList<>();

    @Where(clause = "account_type = 'CREDIT'")
    @OneToMany(mappedBy = "client")
    @Setter(value = AccessLevel.NONE)
    private List<Account> creditAccounts = new ArrayList<>();

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    @Filter(
            name="activeAccount",
            condition="active_status = :active"
    )
    private List<Account> accounts = new ArrayList<>( );
}
