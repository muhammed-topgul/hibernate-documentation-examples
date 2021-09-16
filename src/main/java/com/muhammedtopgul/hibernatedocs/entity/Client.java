package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

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

    private String name;

    @Where(clause = "account_type = 'DEBIT'")
    @OneToMany(mappedBy = "client")
    @Setter(value = AccessLevel.NONE)
    private List<Account> debitAccounts = new ArrayList<>();

    @Where(clause = "account_type = 'CREDIT'")
    @OneToMany(mappedBy = "client")
    @Setter(value = AccessLevel.NONE)
    private List<Account> creditAccounts = new ArrayList<>();
}
