package com.muhammedtopgul.hibernatedocs.immutability.config;

import com.muhammedtopgul.hibernatedocs.config.Config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:09
 */

public class HibernateConfig implements Config {

    private static final SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

}
