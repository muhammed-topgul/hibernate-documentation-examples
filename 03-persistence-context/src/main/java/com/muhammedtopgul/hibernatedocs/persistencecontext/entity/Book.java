package com.muhammedtopgul.hibernatedocs.persistencecontext.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * @author muhammed-topgul created at 12/10/2021 14:43
 */

@Entity(name = "Book")
@Table(schema = "persistence_context", name = "book")
@Getter
@Setter
public class Book extends BaseId {

    private String name;

    @NaturalId
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
