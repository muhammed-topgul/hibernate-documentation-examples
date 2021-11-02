package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author muhammed-topgul created at 23/09/2021 23:05
 */

@Entity(name = "Account")
@Table(schema = "associations", name = "account")
@Getter
@Setter
public class Account extends BaseId {

    @NaturalId
    private String registrationNumber;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountClient> accountClients = new ArrayList<>();

    public void addClient(Client client) {
        AccountClient accountClient = new AccountClient();
        accountClient.setAccount(this);
        accountClient.setClient(client);
        accountClients.add(accountClient);
    }

    public void removeClient(Client client) {
        AccountClient accountClient = new AccountClient();
        accountClient.setAccount(this);
        accountClient.setClient(client);
        accountClients.remove(accountClient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(registrationNumber, account.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
