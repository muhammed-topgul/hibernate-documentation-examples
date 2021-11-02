package com.muhammedtopgul.hibernatedocs.config;

import org.hibernate.SessionFactory;

/**
 * @author muhammed-topgul created at 20/09/2021 15:02
 */

public interface Config {

     SessionFactory getSessionFactory();
}
