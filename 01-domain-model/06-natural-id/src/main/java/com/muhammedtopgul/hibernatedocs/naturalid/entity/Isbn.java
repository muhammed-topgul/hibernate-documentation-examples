package com.muhammedtopgul.hibernatedocs.naturalid.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * created by Muhammed Topgul on 29/09/2021 at 08:27
 */

@Embeddable
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
