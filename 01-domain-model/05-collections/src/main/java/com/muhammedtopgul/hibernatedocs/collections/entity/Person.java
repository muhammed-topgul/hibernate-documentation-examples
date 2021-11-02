package com.muhammedtopgul.hibernatedocs.collections.entity;

import com.muhammedtopgul.hibernatedocs.collections.entity.embeddable.Phone;
import com.muhammedtopgul.hibernatedocs.commons.BaseId;
import lombok.Getter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author muhammed-topgul created at 25/09/2021 22:32
 */

@Entity(name = "Person")
@Table(schema = "collections", name = "person")
@Getter
public class Person extends BaseId {

    @ElementCollection
    @JoinTable(schema = "collections", name = "string_person_phone")
    @OrderColumn(name = "order_id")
    private List<String> stringPhones = new ArrayList<>();

    @ElementCollection
    @JoinTable(schema = "collections", name = "embeddable_person_phone")
    private List<Phone> embeddablePhones = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortNatural
    private SortedSet<Article> articles = new TreeSet<>();

    public void addArticle(Article article) {
        this.articles.add(article);
        article.setPerson(this);
    }

    public void addStringPhone(String phone) {
        this.getStringPhones().add(phone);
    }

    public void addEmbeddablePhones(Phone phone) {
        this.getEmbeddablePhones().add(phone);
    }
}
