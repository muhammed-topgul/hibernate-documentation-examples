package com.muhammedtopgul.hibernatedocs.naturalid.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author muhammed-topgul created at 29/09/2021 08:27
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Isbn implements Serializable {

    private String isbn10;

    private String isbn13;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Isbn isbn = (Isbn) o;
        return Objects.equals(isbn10, isbn.isbn10) && Objects.equals(isbn13, isbn.isbn13);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn10, isbn13);
    }
}
