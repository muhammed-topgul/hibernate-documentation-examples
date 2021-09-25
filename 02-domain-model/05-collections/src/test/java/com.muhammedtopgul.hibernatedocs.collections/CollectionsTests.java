package com.muhammedtopgul.hibernatedocs.collections;

import com.muhammedtopgul.hibernatedocs.collections.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.collections.entity.Person;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.persistAndReturnSession;

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
}
