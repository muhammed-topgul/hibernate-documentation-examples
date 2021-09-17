package com.muhammedtopgul.hibernatedocs.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * created by Muhammed Topgul on 17/09/2021 at 14:44
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publisher {

    @Column(name = "publisher_name")
    private String name;

    @Column(name = "publisher_country")
    private String country;
}
