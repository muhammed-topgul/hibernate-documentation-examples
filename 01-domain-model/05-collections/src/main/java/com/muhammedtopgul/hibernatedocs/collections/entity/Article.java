package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * created by Muhammed Topgul on 27/09/2021 at 22:21
 */

@Entity(name = "Article")
@Table(schema = "collections", name = "article")
@Getter
@Setter
public class Article extends BaseId implements Comparable<Article> {

    private String type;

    @NaturalId
    private String title;

    @ManyToOne
    private Person person;

    @Override
    public int compareTo(Article o) {
        return title.compareTo(o.getTitle());
    }
}
