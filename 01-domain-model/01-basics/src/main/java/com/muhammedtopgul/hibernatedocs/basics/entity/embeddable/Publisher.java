package com.muhammedtopgul.hibernatedocs.basics.entity.embeddable;

import com.muhammedtopgul.hibernatedocs.basics.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
}
