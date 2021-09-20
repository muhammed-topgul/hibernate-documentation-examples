package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * created by Muhammed Topgul on 17/09/2021 at 16:38
 */

@Entity(name = "Library")
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
