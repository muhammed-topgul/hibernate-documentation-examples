package com.muhammedtopgul.hibernatedocs.identifiers;

import com.muhammedtopgul.hibernatedocs.identifiers.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.identifiers.entity.PK;
import com.muhammedtopgul.hibernatedocs.identifiers.entity.Subsystem;
import com.muhammedtopgul.hibernatedocs.identifiers.entity.SystemUser;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.persist;

/**
 * @author muhammed-topgul created at 20/09/2021 16:52
 */

public class IdentifiersTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testCompositeKey() {
        Subsystem subsystem = new Subsystem();
        subsystem.setDescription("windows 10 machine");
        persist(subsystem);

        PK pk = new PK(subsystem, "muhammed.topgul");

        SystemUser systemUser = new SystemUser();
        systemUser.setPk(pk);
        systemUser.setName("Muhammed Topgul");

        persist(systemUser);
    }
}
