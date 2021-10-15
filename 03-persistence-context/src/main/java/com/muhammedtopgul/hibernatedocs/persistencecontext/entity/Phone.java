package com.muhammedtopgul.hibernatedocs.persistencecontext.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * created by Muhammed Topgul on 15/10/2021 at 15:51
 */

@Entity(name = "Phone")
@Table(schema = "persistence_context", name = "phone")
@Getter
@Setter
public class Phone extends BaseId {

    @Column(name = "`number`")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;
}
