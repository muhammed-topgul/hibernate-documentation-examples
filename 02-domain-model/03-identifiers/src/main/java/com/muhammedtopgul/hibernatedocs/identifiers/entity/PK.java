package com.muhammedtopgul.hibernatedocs.identifiers.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * created by Muhammed Topgul on 20/09/2021 at 17:00
 */

@Embeddable
@AllArgsConstructor
@Getter
@Setter
public class PK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private Subsystem subsystem;

    private String username;

    private PK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PK pk = (PK) o;
        return Objects.equals(subsystem, pk.subsystem) &&
                Objects.equals(username, pk.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subsystem, username);
    }
}
