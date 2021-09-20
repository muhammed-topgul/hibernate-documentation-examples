package com.muhammedtopgul.hibernatedocs.utility;

import com.muhammedtopgul.hibernatedocs.config.Config;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:10
 */

public class HibernateUtil {

    @Setter
    private static Config config;

    private HibernateUtil() {
    }

    public static Object persist(Object object) {
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        sessionPersist(session, object);
        transactionCommit(transaction);
        sessionClose(session);

        return object;
    }

    public static <T> T get(Class<T> clazz, String id) {
        return getSession().get(clazz, id);
    }

    public static Session getSession() {
        if (config == null)
            throw new NullPointerException("Config implementation must be set before create session.");
        return config.getSessionFactory().openSession();
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