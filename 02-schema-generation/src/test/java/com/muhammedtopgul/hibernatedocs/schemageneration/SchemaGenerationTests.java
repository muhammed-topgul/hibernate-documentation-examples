package com.muhammedtopgul.hibernatedocs.schemageneration;

import com.muhammedtopgul.hibernatedocs.schemageneration.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.schemageneration.entity.Author;
import com.muhammedtopgul.hibernatedocs.schemageneration.entity.Book;
import com.muhammedtopgul.hibernatedocs.schemageneration.entity.Person;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static org.junit.Assert.assertEquals;

/**
 * created by Muhammed Topgul on 12/10/2021 at 13:09
 */

public class SchemaGenerationTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test(expected = Exception.class)
    public void testCheckConstraint() {
        Book book = new Book();
        book.setPrice(49.99d);
        book.setTitle("High-Performance Java Persistence");
        book.setIsbn("11-11-2016");
        persist(book);
    }

    @Test
    public void testDefaultValue() {
        Person person = new Person();
        persist(person);

        person = get(Person.class, person.getId());
        assertEquals("N/A", person.getName());
        assertEquals(Long.valueOf(-1L), person.getClientId());
    }

    @Test(expected = Exception.class)
    public void testColumnsUniqueConstraints() {
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        Author author = new Author();
        author.setFirstName("Muhammed");
        author.setLastName("Topgul");
        session.persist(author);

        Book book1 = new Book();
        book1.setAuthor(author);
        book1.setTitle("How to Java");

        Book book2 = new Book();
        book2.setAuthor(author);
        book2.setTitle("How to Java");

        session.persist(book1);
        session.persist(book2);
        transaction.commit();
        session.close();
    }
}
