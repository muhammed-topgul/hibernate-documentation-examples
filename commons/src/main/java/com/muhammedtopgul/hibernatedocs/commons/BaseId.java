package com.muhammedtopgul.hibernatedocs.commons;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * created by Muhammed Topgul on 16/09/2021 at 11:08
 */

@MappedSuperclass
@Getter
@Setter
public abstract class BaseId implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;
}