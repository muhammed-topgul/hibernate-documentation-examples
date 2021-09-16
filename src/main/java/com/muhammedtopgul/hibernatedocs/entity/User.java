package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * created by Muhammed Topgul on 16/09/2021 at 15:20
 */

@Entity(name = "User_")
@SQLDelete(sql = "UPDATE User_ SET deleted = true, active = false WHERE id = ?")
@Getter
@Setter
public class User extends BaseId {

    private String username;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @ManyToOne
    @JoinFormula("REGEXP_REPLACE(phoneNumber, '\\+(\\d+)-.*', '\\1')::int")
    private Country country;


    private boolean active;

    private boolean deleted;
}
