package com.muhammedtopgul.hibernatedocs.basics.user;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

/**
 * @author muhammed-topgul created at 16/09/2021 11:15
 */

public class LoggedUserGenerator implements ValueGenerator<String> {

    @Override
    public String generateValue(Session session, Object owner) {
        return CurrentUser.INSTANCE.get();
    }
}
