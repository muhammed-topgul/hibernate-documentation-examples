package com.muhammedtopgul.hibernatedocs.associations;

import com.muhammedtopgul.hibernatedocs.associations.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.associations.entity.*;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;

/**
 * created by Muhammed Topgul on 21/09/2021 at 13:39
 */

public class AssociationsTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testAssociations() {
        // write
        Phone phone = new Phone();
        phone.setNumber("123-456-7890");

        Person person = new Person();
        person.setName("Muhammed Topgul");
        person.addPhone(phone);
        persist(person);

        // remove
        remove(Person.class, person.getId());
    }

    @Test
    public void testCascading() {
        // write
        Phone phone = new Phone();
        phone.setNumber("123-456-7890");

        Person person = new Person();
        person.setName("Muhammed Topgul");
        person.addPhone(phone);
        persist(person);

        // remove
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        person = session.get(Person.class, person.getId());
        phone = session.get(Phone.class, phone.getId());

        person.removePhone(phone);
        session.persist(person);
        transaction.commit();
        session.close();
    }

    @Test
    public void testBidirectionalCascading() {
        Country country = new Country();
        country.setName("Turkey");

        City ankara = new City();
        ankara.setCode("06");
        ankara.setName("Ankara");

        City istanbul = new City();
        istanbul.setCode("34");
        istanbul.setName("Istanbul");

        country.addCity(ankara);
        country.addCity(istanbul);

        Session session = persistAndReturnSession(country);
        Transaction transaction = getTransaction(session);

        country.removeCity(ankara);
        session.persist(country);
        transactionCommit(transaction);
    }

    @Test
    public void testOneToOneUnidirectional() {
        PhoneDetail phoneDetail = new PhoneDetail();
        phoneDetail.setTechnology("5G");
        phoneDetail.setProvider("Huawei");
        persist(phoneDetail);

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        phone.setPhoneDetail(phoneDetail);

        persist(phone);
    }
}
