package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseTimestamp;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Name;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.net.URL;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:27
 */

@Entity(name = "Contact")
@Table(schema = "basics", name = "contact")
@Getter
@Setter
public class Contact extends BaseTimestamp {

    private Name name;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private String notes;

    private URL website;

    @Column(name = "RATING")
    private boolean starred;

    @Type(type = "yes_no") // will keep Y or N instead of true or false in database
    private boolean isActive;
}
