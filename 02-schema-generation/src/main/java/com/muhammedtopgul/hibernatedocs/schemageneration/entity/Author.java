package com.muhammedtopgul.hibernatedocs.schemageneration.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 12/10/2021 13:18
 */

@Entity(name = "Author")
@Table(
        schema = "schema_generation",
        name = "author",
        indexes =  @Index(
                name = "idx_author_first_last_name",
                columnList = "first_name, last_name",
                unique = false
        ))
@Getter
@Setter
public class Author extends BaseId {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
