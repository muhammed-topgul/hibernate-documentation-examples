package com.muhammedtopgul.hibernatedocs.persistencecontext.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul created at 12/10/2021 14:16
 */

@Entity(name = "Person")
@Table(schema = "persistence_context", name = "person")
@Getter
@Setter
public class Person extends BaseId {

    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setPerson(this);
    }
}
