package com.muhammedtopgul.hibernatedocs.basics.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * created by Muhammed Topgul on 17/09/2021 at 16:38
 */

@Entity(name = "Library")
@Table(schema = "basics", name = "library")
@Getter
@Setter
public class Library extends BaseId {

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        this.getBooks().add(book);
    }
}
