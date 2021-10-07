package com.muhammedtopgul.hibernatedocs.inheritace;

import com.muhammedtopgul.hibernatedocs.inheritace.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.sessionClose;

/**
 * created by Muhammed Topgul on 07/10/2021 at 13:00
 */

public class InheritanceTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testHibernateSetup() {
        Session session = getSession();

        transactionCommit(getTransaction(session));

        sessionClose(session);
        new HibernateConfig().getSessionFactory().close();
    }
}
