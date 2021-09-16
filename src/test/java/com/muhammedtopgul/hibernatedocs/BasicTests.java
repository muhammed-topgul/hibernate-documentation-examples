package com.muhammedtopgul.hibernatedocs;

import com.muhammedtopgul.hibernatedocs.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.entity.*;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Name;
import com.muhammedtopgul.hibernatedocs.enumeration.AccountType;
import com.muhammedtopgul.hibernatedocs.enumeration.Gender;
import com.muhammedtopgul.hibernatedocs.enumeration.PhoneType;
import com.muhammedtopgul.hibernatedocs.user.CurrentUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;
import java.util.Date;

import static com.muhammedtopgul.hibernatedocs.util.HibernateUtil.*;
import static org.junit.Assert.assertEquals;

/**
 * created by Muhammed Topgul on 13/09/2021 at 09:12
 */

public class BasicTests {

    @Before
    public void beforeAll() {
        CurrentUser.INSTANCE.logIn("Muhammed");
    }

    @Test
    public void testHibernateSetup() {
        Session session = getSession();

        transactionCommit(getTransaction(session));

        sessionClose(session);
        HibernateConfig.getSessionFactory().close();
    }

    @Test
    public void persistContact() throws MalformedURLException {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Contact contact = new Contact();
        Name name = new Name();
        name.setFirst("Muhammed");
        name.setLast("Topgul");
        contact.setId(1);
        contact.setName(name);
        contact.setActive(true);
        contact.setStarred(true);
        contact.setNotes("Contact detail notes");
        contact.setWebsite(new URL("https://github.com/muhammed-topgul"));

        session.persist(contact);
        transaction.commit();
        session.close();
    }

    @Test
    public void testBasicTypeConversion() {
        BitSet bitSet = BitSet.valueOf(new long[]{1, 2, 3});

        Session session = getSession();
        Transaction transaction = getTransaction(session);

        Product product = new Product();
        product.setId(1);
        product.setBitSet(bitSet);

        session.persist(product);
        transaction.commit();

        // read
        product = session.get(Product.class, 1);
        assertEquals(bitSet, product.getBitSet());
    }

    @Test
    public void testEnumeratedColumn() {
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        Phone phone = new Phone();
        phone.setId(1);
        phone.setNumber("123-456-78990");
        phone.setType(PhoneType.MOBILE);

        session.persist(phone);
        transaction.commit();

        session.close();
    }

    @Test
    public void testAttributeConverter() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Name name = new Name();
        name.setFirst("Muhammed");
        name.setLast("Topgul");

        Person person = new Person();
        person.setId(1);
        person.setName(name);
        person.setGender(Gender.MALE);

        session.persist(person);
        transaction.commit();

        person = session.get(Person.class, 1);

        assertEquals(person.getGender(), Gender.MALE);

        session.close();
    }

    @Test
    public void testClob() {
        String warranty = "My product warranty";

        Product product = new Product();
        product.setId(1);
        product.setName("Mobile phone");
        product.setWarranty(warranty);

        persist(product);

        product = new Product();

        product = (Product) get(product, 1);
        assertEquals("My product warranty", product.getWarranty());
    }

    @Test
    public void testDate() {
        DateEvent dateEvent = new DateEvent();
        dateEvent.setTimestamp(new Date());

        persist(dateEvent);
    }

    @Test
    public void testCustomCreationTimestamp() {
        Event event = new Event();
        event.setId(1);
        persist(event);
    }

    @Test
    public void testFormula() {
        Account account = new Account();
        account.setId(1);
        account.setCredit(10.4);
        account.setRate(2.0);

        persist(account);

        account = (Account) get(account, account.getId());
        assertEquals(String.valueOf(account.getCredit() * account.getRate()), account.getInterest().toString());
    }

    @Test
    public void testWhereClause() {
        // write
        Client client = new Client();
        client.setId(1);
        client.setName("Muhammed Topgul");
        persist(client);

        Account account001 = new Account();
        account001.setId(1);
        account001.setActive(true);
        account001.setAmount(1000.0);
        account001.setRate(0.9);
        account001.setCredit(12.00);
        account001.setType(AccountType.CREDIT);
        account001.setClient(client);
        persist(account001);

        Account account002 = new Account();
        account002.setId(2);
        account002.setActive(true);
        account002.setAmount(2000.0);
        account002.setRate(0.15);
        account002.setCredit(9.0);
        account002.setType(AccountType.DEBIT);
        account002.setClient(client);

        persist(account002);

        // read
        client = (Client) get(client, 1);
        assertEquals(1, client.getCreditAccounts().size());
        assertEquals(1, client.getDebitAccounts().size());
    }

    @Before
    public void afterAll() {
        CurrentUser.INSTANCE.logOut();
    }
}
