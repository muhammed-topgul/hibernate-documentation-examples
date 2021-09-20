package com.muhammedtopgul.hibernatedocs.basics.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * created by Muhammed Topgul on 15/09/2021 at 17:20
 */

@AllArgsConstructor
@Getter
public enum Gender {

    MALE('M'),
    FEMALE('F');

    private final char code;

    public static Gender fromCode(char code) {
        if (code == 'M' || code == 'm') {
            return MALE;
        }
        if (code == 'F' || code == 'f') {
            return FEMALE;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }
}
