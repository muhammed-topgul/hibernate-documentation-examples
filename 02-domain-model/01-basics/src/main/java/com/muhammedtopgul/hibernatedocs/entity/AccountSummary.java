package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;

/**
 * created by Muhammed Topgul on 20/09/2021 at 10:59
 */

@Entity(name = "AccountSummary")
@Subselect(
        "select " +
                "    a.id as id, " +
                "    concat(concat(c.firstname, ' '), c.lastname) as clientName, " +
                "    sum(at.cents) as balance " +
                "from basics.account a " +
                "join basics.client c on c.id = a.account_client_id " +
                "join basics.account_transaction at on a.id = at.account_id " +
                "group by a.id, concat(concat(c.firstname, ' '), c.lastname)"
)
@Synchronize({"client", "account", "account_transaction"})
@Getter
@Setter
@ToString
public class AccountSummary extends BaseId {

    private String clientName;

    private int balance;
}
