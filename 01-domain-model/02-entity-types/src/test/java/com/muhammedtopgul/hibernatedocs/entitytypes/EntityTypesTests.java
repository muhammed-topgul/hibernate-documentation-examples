package com.muhammedtopgul.hibernatedocs.entitytypes;

/**
 * @author muhammed-topgul created at 20/09/2021 13:01
 */

import com.muhammedtopgul.hibernatedocs.entitytypes.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.entitytypes.entity.Book;
import com.muhammedtopgul.hibernatedocs.entitytypes.entity.Identifiable;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.getSession;
import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.persist;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityTypesTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

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
