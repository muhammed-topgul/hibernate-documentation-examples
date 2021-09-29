package com.muhammedtopgul.hibernatedocs.naturalid;

import com.muhammedtopgul.hibernatedocs.naturalid.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.naturalid.entity.Book;
import com.muhammedtopgul.hibernatedocs.naturalid.entity.Isbn;
import com.muhammedtopgul.hibernatedocs.naturalid.entity.Publisher;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.persist;

/**
 * created by Muhammed Topgul on 29/09/2021 at 08:29
 */

public class NaturalIdsTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testEmbeddedNaturalId() {
        Isbn isbn = new Isbn();
        isbn.setIsbn10("isbn0010");
        isbn.setIsbn13("isbn0013");

        Book book = new Book();
        book.setAuthor("Muhammed");
        book.setTitle("How to Java");
        book.setIsbn(isbn);

        persist(book);
    }

    @Test
    public void testMultipleNaturalId() {
        Publisher publisher = new Publisher();
        publisher.setName("ACE");

        Isbn isbn = new Isbn();
        isbn.setIsbn10("isbn0010");
        isbn.setIsbn13("isbn0013");

        Book book = new Book();
        book.setAuthor("Muhammed");
        book.setTitle("How to Java");
        book.setIsbn(isbn);
        book.setPublisher(publisher);

        persist(book);
    }
}
