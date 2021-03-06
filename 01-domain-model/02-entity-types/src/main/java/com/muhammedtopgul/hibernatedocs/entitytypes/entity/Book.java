package com.muhammedtopgul.hibernatedocs.entitytypes.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 20/09/2021 13:02
 */

@Entity(name = "Book")
@Table(schema = "entity_types")
@Proxy(proxyClass = Identifiable.class)
@Getter
@Setter
@ToString
public final class Book extends BaseId implements Identifiable {

    private String title;

    private String author;

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
