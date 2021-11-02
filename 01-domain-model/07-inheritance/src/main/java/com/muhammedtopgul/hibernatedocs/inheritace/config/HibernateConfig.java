package com.muhammedtopgul.hibernatedocs.inheritace.config;

import com.muhammedtopgul.hibernatedocs.config.Config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author muhammed-topgul created at 13/09/2021 09:09
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
