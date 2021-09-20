package com.muhammedtopgul.hibernatedocs;

/**
 * created by Muhammed Topgul on 20/09/2021 at 13:01
 */

import com.muhammedtopgul.hibernatedocs.entity.Book;
import com.muhammedtopgul.hibernatedocs.entity.Identifiable;
import org.hibernate.Session;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.util.HibernateUtil.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityTypeTests {

    @Test
    public void testCustomProxyClass() {
        // write
        Book book = new Book();
        book.setTitle("High-Performance Java Persistence");
        book.setAuthor("Vlad Mihalcea");
        persist(book);

        // read
        Session session = getSession();
        Identifiable identifiable = session.load(Book.class, book.getId());

        System.out.println(identifiable.getClass().getName());

        assertTrue(
                "Loaded entity is not an instance of the proxy interface",
                identifiable instanceof Identifiable
        );

        assertFalse(
                "Proxy class was not created",
                identifiable instanceof Book
        );
    }
}
