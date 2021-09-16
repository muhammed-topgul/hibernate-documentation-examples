package com.muhammedtopgul.hibernatedocs.util;

import com.muhammedtopgul.hibernatedocs.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:10
 */

public class HibernateUtil {

    public static Object persist(Object object) {
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        sessionPersist(session, object);
        transactionCommit(transaction);
        sessionClose(session);

        return object;
    }

    public static Object get(Object object, Integer id) {
        if (object instanceof Product) {
            return getSession().get(Product.class, id);
        } else if (object instanceof Contact) {
            return getSession().get(Contact.class, id);
        } else if (object instanceof Person) {
            return getSession().get(Person.class, id);
        } else if (object instanceof Phone) {
            return getSession().get(Phone.class, id);
        } else if (object instanceof Account) {
            return getSession().get(Account.class, id);
        } else if (object instanceof Client) {
            return getSession().get(Client.class, id);
        }

        throw new UnsupportedOperationException(object.getClass().getName() + " is not present");
    }

    public static Session getSession() {
        return HibernateConfig.getSessionFactory().openSession();
    }

    public static Transaction getTransaction(Session session) {
        return session.beginTransaction();
    }

    public static void sessionPersist(Session session, Object object) {
        session.persist(object);
    }

    public static void transactionCommit(Transaction transaction) {
        transaction.commit();
    }

    public static void sessionClose(Session session) {
        session.close();
    }

    public static void sessionFlush(Session session) {
        session.flush();
    }
}
