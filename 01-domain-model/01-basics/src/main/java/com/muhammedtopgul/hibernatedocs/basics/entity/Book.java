package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.Publisher;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 17/09/2021 14:45
 */

@Entity(name = "Book")
@Table(schema = "basics", name = "book")
@Getter
@Setter
public class Book extends BaseId {

    private String title;

    private String author;

    @Embedded
    private Publisher publisher;
}
