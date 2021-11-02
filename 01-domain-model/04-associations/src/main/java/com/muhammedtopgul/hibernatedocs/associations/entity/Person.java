package com.muhammedtopgul.hibernatedocs.associations.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul created at 21/09/2021 16:02
 */

@Entity(name = "Person")
@Table(schema = "associations", name = "person")
@Getter
@Setter
public class Person extends BaseId {

    private String name;

    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(schema = "associations", name = "person_phone")
    private List<Phone> phones = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "person_address",
            schema = "associations",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")})
    private List<Address> addresses = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
            name = "cityName",
            referencedColumnName = "name",
            insertable = false,
            updatable = false
    )
    private City city;

    public void addPhone(Phone phone) {
        this.getPhones().add(phone);
    }

    public void removePhone(Phone phone) {
        this.getPhones().remove(phone);
    }

    public void addAddress(Address address) {
        this.getAddresses().add(address);
    }
}
