package com.muhammedtopgul.hibernatedocs.basics.entity.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @author muhammed-topgul created at 13/09/2021 09:27
 */

@Embeddable
@Getter
@Setter
public class Name {

    private String first;

    private String middle;

    private String last;
}
