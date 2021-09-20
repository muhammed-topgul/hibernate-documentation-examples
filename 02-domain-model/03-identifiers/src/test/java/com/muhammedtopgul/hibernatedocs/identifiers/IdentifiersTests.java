package com.muhammedtopgul.hibernatedocs.identifiers;

import com.muhammedtopgul.hibernatedocs.identifiers.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.identifiers.entity.PK;
import com.muhammedtopgul.hibernatedocs.identifiers.entity.SystemUser;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.persist;

/**
 * created by Muhammed Topgul on 20/09/2021 at 16:52
 */

public class IdentifiersTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testCompositeKey() {
        PK pk = new PK("windows", "muhammed.topgul");

        SystemUser systemUser = new SystemUser();
        systemUser.setPk(pk);
        systemUser.setName("Muhammed Topgul");

        persist(systemUser);
    }
}
