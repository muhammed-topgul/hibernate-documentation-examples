package com.muhammedtopgul.hibernatedocs.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * created by Muhammed Topgul on 16/09/2021 at 17:29
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class GPS implements Coordinates {

    private double latitude;

    private double longitude;

    @Override
    public double x() {
        return latitude;
    }

    @Override
    public double y() {
        return longitude;
    }
}
