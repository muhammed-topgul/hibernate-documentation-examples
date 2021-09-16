package com.muhammedtopgul.hibernatedocs.entity.base;

import lombok.Getter;
import lombok.Setter;

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
    private Integer id;
}
