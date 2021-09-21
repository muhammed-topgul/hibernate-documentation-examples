package com.muhammedtopgul.hibernatedocs.associations;

import com.muhammedtopgul.hibernatedocs.associations.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.associations.entity.Person;
import com.muhammedtopgul.hibernatedocs.associations.entity.Phone;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.persist;

/**
 * created by Muhammed Topgul on 21/09/2021 at 13:39
 */

public class AssociationsTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testManyToOne() {
        Person person = new Person();
        person.setName("Muhammed Topgul");
        persist(person);

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        phone.setPerson(person);
        persist(phone);
    }
}
