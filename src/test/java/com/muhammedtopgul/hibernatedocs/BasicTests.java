package com.muhammedtopgul.hibernatedocs;

import com.muhammedtopgul.hibernatedocs.config.HibernateConfig;
import org.hibernate.Session;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.util.HibernateUtil.*;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:12
 */

public class BasicTests {

    @Test
    public void testHibernateSetup() {
        Session session = getSession();

        transactionCommit(getTransaction(session));

        sessionClose(session);
        HibernateConfig.getSessionFactory().close();
    }
}
