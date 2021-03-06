package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.entity.base.BaseTimestamp;
import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.Name;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.net.URL;

/**
 * @author muhammed-topgul created at 13/09/2021 09:27
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
