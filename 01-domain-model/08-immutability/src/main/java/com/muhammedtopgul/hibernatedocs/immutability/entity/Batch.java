package com.muhammedtopgul.hibernatedocs.immutability.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muhammed-topgul created at 12/10/2021 11:50
 */

@Entity(name = "Batch")
@Table(schema = "immutability", name = "batch")
@Getter
@Setter
public class Batch extends BaseId {

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "batch_id")
    @Immutable
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
