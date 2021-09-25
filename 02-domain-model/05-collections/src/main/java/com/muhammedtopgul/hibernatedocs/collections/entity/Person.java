package com.muhammedtopgul.hibernatedocs.collections.entity;

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
    @JoinTable(schema = "collections", name = "person_phone")
    @OrderColumn(name = "order_id")
    private List<String> phones = new ArrayList<>();

    public void addPhone(String phone) {
        this.getPhones().add(phone);
    }
}
