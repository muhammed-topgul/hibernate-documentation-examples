package com.muhammedtopgul.hibernatedocs.entity;

import com.muhammedtopgul.hibernatedocs.entity.base.BaseId;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Publisher;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * created by Muhammed Topgul on 17/09/2021 at 14:45
 */

@Entity(name = "Book")
@Getter
@Setter
public class Book extends BaseId {

    private String title;

    private String author;

    @Embedded
    private Publisher publisher;
}
