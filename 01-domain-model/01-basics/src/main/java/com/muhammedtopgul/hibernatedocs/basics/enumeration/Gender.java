package com.muhammedtopgul.hibernatedocs.basics.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author muhammed-topgul created at 15/09/2021 17:20
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
