package com.muhammedtopgul.hibernatedocs.naturalid.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * created by Muhammed Topgul on 29/09/2021 at 08:26
 */

@Entity(name = "Book")
@Table(schema = "natural_ids", name = "book")
@Getter
@Setter
@ToString
public class Book extends BaseId {

    private String title;

    private String author;

    @NaturalId
    @Embedded
    private Isbn isbn;

    @NaturalId
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Publisher publisher;
}
