package com.muhammedtopgul.hibernatedocs.identifiers;

import com.muhammedtopgul.hibernatedocs.identifiers.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.junit.Before;

/**
 * created by Muhammed Topgul on 20/09/2021 at 16:52
 */

public class IdentifiersTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }
}
