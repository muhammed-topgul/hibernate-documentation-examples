package com.muhammedtopgul.hibernatedocs.collections;

import com.muhammedtopgul.hibernatedocs.collections.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.collections.entity.Person;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;

/**
 * created by Muhammed Topgul on 25/09/2021 at 22:34
 */

public class CollectionsTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test(expected = ClassCastException.class)
    public void testCollection() {
        Person person = new Person();
        person.addPhone("132-456-78900");

        Session session = persistAndReturnSession(person);

        person = session.get(Person.class, person.getId());
        ArrayList<String> phones = (ArrayList<String>) person.getPhones();
    }

    @Test
    public void testRemoveElementCollectionUsingOrderColumn() {
        Person person = new Person();
        person.addPhone("132-456-78900");
        person.addPhone("132-456-78901");
        person.addPhone("132-456-78902");

        Session session = persistAndReturnSession(person);
        Transaction transaction = getTransaction(session);

        person = session.get(Person.class, person.getId());
        person.getPhones().remove(0);

        sessionPersist(session, person);
        transactionCommit(transaction);
    }
}