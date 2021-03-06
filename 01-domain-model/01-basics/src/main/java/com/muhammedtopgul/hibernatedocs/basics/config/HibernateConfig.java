package com.muhammedtopgul.hibernatedocs.basics.config;

import com.muhammedtopgul.hibernatedocs.config.Config;
import com.muhammedtopgul.hibernatedocs.basics.converter.basicType.GenderType;
import com.muhammedtopgul.hibernatedocs.basics.converter.userType.BitSetUserType;
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

        // for type conversion
        configuration.registerTypeContributor((typeContributions, serviceRegistry) -> {
            // typeContributions.contributeType(BitSetType.INSTANCE); for BasicType conversion
            typeContributions.contributeType(BitSetUserType.INSTANCE, "bitset");
            typeContributions.contributeType(GenderType.INSTANCE, "genderType");
        });

        sessionFactory = configuration.buildSessionFactory();
    }

}
