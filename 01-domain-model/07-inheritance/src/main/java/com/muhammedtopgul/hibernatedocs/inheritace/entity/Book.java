package com.muhammedtopgul.hibernatedocs.inheritace.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author muhammed-topgul created at 12/10/2021 11:15
 */

@Entity(name = "Book")
@Table(schema = "inheritances", name = "book")
@Getter
@Setter
public class Book implements DomainModelEntity<Long> {

    @Id
    @Getter(AccessLevel.NONE)
    private Long id;

    @Version
    @Getter(AccessLevel.NONE)
    private Integer version;

    private String title;

    private String author;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Integer getVersion() {
        return this.version;
    }
}
