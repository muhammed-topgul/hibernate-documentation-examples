package com.muhammedtopgul.hibernatedocs.immutability;

import com.muhammedtopgul.hibernatedocs.immutability.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.immutability.entity.Batch;
import com.muhammedtopgul.hibernatedocs.immutability.entity.Event;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static org.junit.Assert.assertEquals;

/**
 * created by Muhammed Topgul on 12/10/2021 at 11:42
 */

public class ImmutabilityTest {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testEntityImmutability() {
        Event event = new Event();
        event.setCreatedOn(new Date());
        event.setMessage("Hibernate User Guide rocks!");
        persist(event);

        Session session = getSession();
        Transaction transaction = getTransaction(session);

        event = session.get(Event.class, event.getId());
        event.setMessage("Hibernate User Guide");
        System.out.println("Event modified...");
        session.persist(event);
        transaction.commit();
        session.close();

        session = getSession();
        event = session.get(Event.class, event.getId());
        assertEquals("Hibernate User Guide rocks!", event.getMessage());
    }

    @Test
    public void testCollectionImmutability() {
        Batch batch = new Batch();
        batch.setName("Change request");

        Event event1 = new Event();
        event1.setCreatedOn(new Date());
        event1.setMessage("Update Hibernate User Guide");

        Event event2 = new Event();
        event2.setCreatedOn(new Date());
        event2.setMessage("Update Hibernate Getting Started Guide");

        batch.addEvent(event1);
        batch.addEvent(event2);

        persist(batch);

        Session session = getSession();
        Transaction transaction = getTransaction(session);

        // change mutable field
        batch = session.get(Batch.class, batch.getId());
        batch.setName("Change batch name");
        session.persist(batch);
        transaction.commit();
        session.close();

        session = getSession();
        transaction = getTransaction(session);
        batch = session.get(Batch.class, batch.getId());
        assertEquals(batch.getName(), "Change batch name");

        // change immutable collection
        try {
            batch.getEvents().clear();
            session.persist(batch);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.err.println("Immutable collections cannot be modified");
        }
    }
}
