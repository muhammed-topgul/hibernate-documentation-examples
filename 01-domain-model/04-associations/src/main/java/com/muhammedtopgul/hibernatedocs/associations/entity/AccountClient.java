package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author muhammed-topgul created at 23/09/2021 23:07
 */

@Entity(name = "AccountClient")
@Table(schema = "associations", name = "account_client")
@Getter
@Setter
public class AccountClient extends BaseId {

    @Id
    @ManyToOne
    private Account account;

    @Id
    @ManyToOne
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountClient that = (AccountClient) o;
        return Objects.equals(account, that.account) &&
                Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, client);
    }
}
