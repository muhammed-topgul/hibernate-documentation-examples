package com.muhammedtopgul.hibernatedocs.basics.entity.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:27
 */

@Embeddable
@Getter
@Setter
public class Name {

    private String first;

    private String middle;

    private String last;
}
