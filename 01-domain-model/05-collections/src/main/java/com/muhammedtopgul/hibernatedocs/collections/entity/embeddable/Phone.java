package com.muhammedtopgul.hibernatedocs.collections.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * created by Muhammed Topgul on 25/09/2021 at 23:26
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Phone {

    private String type;

    @Column(name = "`number`")
    private String number;
}
