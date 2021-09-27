package com.muhammedtopgul.hibernatedocs.collections;

import com.muhammedtopgul.hibernatedocs.collections.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.collections.entity.City;
import com.muhammedtopgul.hibernatedocs.collections.entity.Country;
import com.muhammedtopgul.hibernatedocs.collections.entity.Person;
import com.muhammedtopgul.hibernatedocs.collections.entity.embeddable.Phone;
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
        person.addStringPhone("132-456-78900");

        Session session = persistAndReturnSession(person);

        person = session.get(Person.class, person.getId());
        ArrayList<String> phones = (ArrayList<String>) person.getStringPhones();
    }

    @Test
    public void testRemoveElementCollectionUsingOrderColumn() {
        Person person = new Person();
        person.addStringPhone("132-456-78900");
        person.addStringPhone("132-456-78901");
        person.addStringPhone("132-456-78902");

        Session session = persistAndReturnSession(person);
        Transaction transaction = getTransaction(session);

        person = session.get(Person.class, person.getId());
        person.getStringPhones().remove(0);

        sessionPersist(session, person);
        transactionCommit(transaction);
        sessionClose(session);
    }

    @Test
    public void testEmbeddableCollections() {
        Person person = new Person();
        person.addEmbeddablePhones(new Phone("landline", "028-234-9876"));
        person.addEmbeddablePhones(new Phone("mobile", "072-122-9876"));
        persist(person);
    }

    @Test
    public void testUnidirectionalBag() {
        City city1 = new City();
        city1.setCode("06");
        city1.setName("Ankara");

        City city2 = new City();
        city2.setCode("34");
        city2.setName("Istanbul");

        Country country = new Country();
        country.setName("Turkey");
        country.addCity(city1);
        country.addCity(city2);

        persist(country);
    }
}
