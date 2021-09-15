package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.embeddable.Name;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:27
 */

@Entity(name = "Contact")
@Getter
@Setter
public class Contact {

    @Id
    private Integer id;

    private Name name;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private String notes;

    private URL website;

    @Column( name = "RATING" )
    private boolean starred;
}
