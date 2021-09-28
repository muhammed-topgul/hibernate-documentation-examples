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
 * created by Muhammed Topgul on 23/09/2021 at 23:06
 */

@Entity(name = "Client")
@Table(schema = "associations", name = "client")
@Getter
@Setter
public class Client extends BaseId {

    private String name;

    @NaturalId
    private String code;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountClient> accountClients = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(code, client.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
