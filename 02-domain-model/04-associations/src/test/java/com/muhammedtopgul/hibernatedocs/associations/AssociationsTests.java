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

    // result will be proper only unidirectional relation
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

    // save two entity in two persist operation
    @Test
    public void testOneToOneBidirectionalFirstSolution() {
        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        persist(phone);

        PhoneDetail phoneDetail = new PhoneDetail();
        phoneDetail.setTechnology("5G");
        phoneDetail.setProvider("Huawei");
        phoneDetail.setPhone(phone);
        persist(phoneDetail);
    }

    @Test
    public void testOneToOneBidirectionalSecondSolution() throws InterruptedException {
        PhoneDetail phoneDetail = new PhoneDetail();
        phoneDetail.setTechnology("5G");
        phoneDetail.setProvider("Huawei");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        phone.addPhoneDetail(phoneDetail);
        Session session = persistAndReturnSession(phone);
        Transaction transaction = getTransaction(session);

        // Thread.sleep(5000);

        phone.removePhoneDetail(phoneDetail);
        session.persist(phone);
        transactionCommit(transaction);
        sessionClose(session);
    }

    @Test
    public void testManyToManyUnidirectional() {
        Person person1 = new Person();
        person1.setName("Muhammed");
        Person person2 = new Person();
        person2.setName("John");

        Address address1 = new Address();
        address1.setStreet("12th Avenue");
        address1.setNumber("12A");

        Address address2 = new Address();
        address2.setStreet("18th Avenue");
        address2.setNumber("18B");

        person1.addAddress(address1);
        person1.addAddress(address2);

        person2.getAddresses().add(address1);

        Session session = getSession();
        Transaction transaction = getTransaction(session);

        session.persist(person1);
        session.persist(person2);

        transactionCommit(transaction);
        sessionClose(session);

        session = getSession();
        transaction = getTransaction(session);
        person1 = session.get(Person.class, person1.getId());
        session.remove(person1);

        transactionCommit(transaction);
        sessionClose(session);
    }
}
