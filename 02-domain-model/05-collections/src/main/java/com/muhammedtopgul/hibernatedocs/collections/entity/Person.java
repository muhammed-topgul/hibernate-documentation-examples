package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.collections.entity.embeddable.Phone;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Muhammed Topgul on 25/09/2021 at 22:32
 */

@Entity(name = "Person")
@Table(schema = "collections", name = "person")
@Getter
public class Person extends BaseId {

    @ElementCollection
    @JoinTable(schema = "collections", name = "string_person_phone")
    @OrderColumn(name = "order_id")
    private List<String> stringPhones = new ArrayList<>();

    @ElementCollection
    @JoinTable(schema = "collections", name = "embeddable_person_phone")
    private List<Phone> embeddablePhones = new ArrayList<>();

    public void addStringPhone(String phone) {
        this.getStringPhones().add(phone);
    }

    public void addEmbeddablePhones(Phone phone) {
        this.getEmbeddablePhones().add(phone);
    }
}
