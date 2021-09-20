package com.muhammedtopgul.hibernatedocs.util;

import com.muhammedtopgul.hibernatedocs.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.entity.Book;
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

    public static Object get(Object object, String id) {
        if (object instanceof Book) {
            return getSession().get(Book.class, id);
        }

        throw new UnsupportedOperationException(object.getClass() + " is not present");
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
