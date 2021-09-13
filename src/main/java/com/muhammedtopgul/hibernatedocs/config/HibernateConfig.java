package com.muhammedtopgul.hibernatedocs.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:09
 */

public class HibernateConfig {

    private static final SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

}
