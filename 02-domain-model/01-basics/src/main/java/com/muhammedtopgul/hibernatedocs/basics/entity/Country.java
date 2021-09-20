package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * created by Muhammed Topgul on 16/09/2021 at 15:52
 */

@Entity(name = "Country")
@Table(schema = "basics", name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country extends BaseId {

    @NaturalId
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        Country country = (Country) o;
        return Objects.equals(getId(), country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}