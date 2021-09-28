package com.muhammedtopgul.hibernatedocs.collections;

import com.muhammedtopgul.hibernatedocs.collections.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.collections.entity.*;
import com.muhammedtopgul.hibernatedocs.collections.entity.embeddable.Phone;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;

/**
 * created by Muhammed Topgul on 25/09/2021 at 22:34
 */

public class CollectionsTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test(expected = ClassCastException.class)
    public void testCollection() {
        Person person = new Person();
        person.addStringPhone("132-456-78900");

        Session session = persistAndReturnSession(person);

        person = session.get(Person.class, person.getId());
        ArrayList<String> phones = (ArrayList<String>) person.getStringPhones();
    }

    @Test
    public void testRemoveElementCollectionUsingOrderColumn() {
        Person person = new Person();
        person.addStringPhone("132-456-78900");
        person.addStringPhone("132-456-78901");
        person.addStringPhone("132-456-78902");

        Session session = persistAndReturnSession(person);
        Transaction transaction = getTransaction(session);

        person = session.get(Person.class, person.getId());
        person.getStringPhones().remove(0);

        sessionPersist(session, person);
        transactionCommit(transaction);
        sessionClose(session);
    }

    @Test
    public void testEmbeddableCollections() {
        Person person = new Person();
        person.addEmbeddablePhones(new Phone("landline", "028-234-9876"));
        person.addEmbeddablePhones(new Phone("mobile", "072-122-9876"));
        persist(person);
    }

    @Test
    public void testUnidirectionalBag() {
        City city1 = new City();
        city1.setCode("06");
        city1.setName("Ankara");

        City city2 = new City();
        city2.setCode("34");
        city2.setName("Istanbul");

        City city3 = new City();
        city3.setCode("58");
        city3.setName("Sivas");

        Country country = new Country();
        country.setName("Turkey");
        country.addCity(city1);
        country.addCity(city2);
        country.addCity(city3);

        persist(country);

        Session session = getSession();

        country = session.get(Country.class, country.getId());
        country.getCities().forEach(city -> System.out.println(city.getName()));
    }

    @Test
    public void testOrderBy() {
        District district1 = new District();
        district1.setName("Çankaya");

        District district2 = new District();
        district2.setName("Keçiören");

        District district3 = new District();
        district3.setName("Pursaklar");

        City city = new City();
        city.setCode("06");
        city.setName("Ankara");
        city.addDistrict(district1);
        city.addDistrict(district2);
        city.addDistrict(district3);

        persist(city);

        Session session = getSession();
        city = session.get(City.class, city.getId());
        city.getDistricts().forEach(district -> System.out.println(district.getName()));
    }

    @Test
    public void testSortedSet() {
        Article article1 = new Article();
        article1.setType("SE");
        article1.setTitle("How to Program Java");

        Article article2 = new Article();
        article2.setType("EE");
        article2.setTitle("Spring In Action");

        Article article3 = new Article();
        article3.setType("EE");
        article3.setTitle("Java Server Pages");

        Person person = new Person();
        person.addArticle(article1);
        person.addArticle(article2);
        person.addArticle(article3);

        persist(person);

        Session session = getSession();
        person = session.get(Person.class, person.getId());
        person.getArticles().forEach(article -> System.out.println(article.getTitle()));
    }
}
