package com.muhammedtopgul.hibernatedocs.persistencecontext;

import com.muhammedtopgul.hibernatedocs.persistencecontext.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Person;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;

/**
 * created by Muhammed Topgul on 12/10/2021 at 14:16
 */

public class PersistenceContextTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testPersistAndDelete() {
        Person person = new Person();
        person.setName("Muhammed");
        persist(person);

        Session session = getSession();
        Transaction transaction = getTransaction(session);
        if (session.contains(person)) {
            System.out.println("Person is in MANAGED state");
        } else {

        }
        session.remove(person);
        transaction.commit();
        session.close();
    }
}
