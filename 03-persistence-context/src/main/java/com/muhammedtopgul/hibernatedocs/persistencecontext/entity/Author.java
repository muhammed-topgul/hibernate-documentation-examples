package com.muhammedtopgul.hibernatedocs.persistencecontext.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author muhammed-topgul created at 12/10/2021 14:42
 */

@Entity(name = "Author")
@Table(schema = "persistence_context", name = "author")
@Getter
@Setter
public class Author extends BaseId {

    private String name;
}
