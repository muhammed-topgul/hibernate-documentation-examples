package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;

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

    private boolean active;

    private boolean deleted;
}
