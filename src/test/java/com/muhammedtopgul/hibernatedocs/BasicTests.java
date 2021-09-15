package com.muhammedtopgul.hibernatedocs;

import com.muhammedtopgul.hibernatedocs.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.entity.Contact;
import com.muhammedtopgul.hibernatedocs.entity.Product;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Name;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;

import static com.muhammedtopgul.hibernatedocs.util.HibernateUtil.*;
import static org.junit.Assert.assertEquals;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:12
 */

public class BasicTests {

    @Test
    public void testHibernateSetup() {
        Session session = getSession();

        transactionCommit(getTransaction(session));

        sessionClose(session);
        HibernateConfig.getSessionFactory().close();
    }

    @Test
    public void persistContact() throws MalformedURLException {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Contact contact = new Contact();
        Name name = new Name();
        name.setFirst("Muhammed");
        name.setLast("Topgul");
        contact.setId(1);
        contact.setName(name);
        contact.setActive(true);
        contact.setStarred(true);
        contact.setNotes("Contact detail notes");
        contact.setWebsite(new URL("https://github.com/muhammed-topgul"));

        session.persist(contact);
        transaction.commit();
        session.close();
    }

    @Test
    public void testBasicTypeConversion() {
        BitSet bitSet = BitSet.valueOf( new long[] {1, 2, 3} );

        Session session = getSession();
        Transaction transaction = getTransaction(session);

        Product product = new Product();
        product.setId(1);
        product.setBitSet(bitSet);

        session.persist(product);
        transaction.commit();

        // read
        product = session.get(Product.class, 1);
        assertEquals(bitSet, product.getBitSet());
    }


}
