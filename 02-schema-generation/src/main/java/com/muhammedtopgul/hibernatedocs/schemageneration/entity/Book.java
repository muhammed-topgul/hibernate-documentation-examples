package com.muhammedtopgul.hibernatedocs.schemageneration.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * @author muhammed-topgul created at 12/10/2021 13:08
 */

@Entity(name = "Book")
@Table(
        schema = "schema_generation",
        name = "book",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_book_title_and_author",
                columnNames = {
                        "title",
                        "author_id"
                }
        ))
@Check(constraints = "CASE WHEN isbn IS NOT NULL THEN LENGTH(isbn) = 13 ELSE true END")
@Getter
@Setter
public class Book extends BaseId {

    private String title;

    @NaturalId
    private String isbn;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "author_id",
            foreignKey = @ForeignKey(name = "fk_book_author_id")
    )
    private Author author;
}