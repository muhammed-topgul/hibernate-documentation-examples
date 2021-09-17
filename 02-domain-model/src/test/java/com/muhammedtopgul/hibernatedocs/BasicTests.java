package com.muhammedtopgul.hibernatedocs;

import com.muhammedtopgul.hibernatedocs.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.entity.*;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.GPS;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Name;
import com.muhammedtopgul.hibernatedocs.entity.embeddable.Publisher;
import com.muhammedtopgul.hibernatedocs.enumeration.AccountType;
import com.muhammedtopgul.hibernatedocs.enumeration.Gender;
import com.muhammedtopgul.hibernatedocs.enumeration.PhoneType;
import com.muhammedtopgul.hibernatedocs.user.CurrentUser;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;
import java.util.Date;
import java.util.List;

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
        contact.setName(name);
        contact.setActive(true);
        contact.setStarred(true);
        contact.setNotes("Contact detail notes");
        contact.setWebsite(new URL("https://github.com/muhammed-topgul"));

        persist(contact);
        transaction.commit();
        session.close();
    }

    @Test
    public void testBasicTypeConversion() {
        BitSet bitSet = BitSet.valueOf(new long[]{1, 2, 3});

        Session session = getSession();
        Transaction transaction = getTransaction(session);

        Product product = new Product();
        product.setBitSet(bitSet);

        persist(product);
        transaction.commit();

        // read
        product = session.get(Product.class, product.getId());
        assertEquals(bitSet, product.getBitSet());
    }

    @Test
    public void testEnumeratedColumn() {
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        Phone phone = new Phone();
        phone.setNumber("123-456-78990");
        phone.setType(PhoneType.MOBILE);

        persist(phone);
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
        person.setName(name);
        person.setGender(Gender.MALE);

        persist(person);
        transaction.commit();

        person = session.get(Person.class, person.getId());

        assertEquals(person.getGender(), Gender.MALE);

        session.close();
    }

    @Test
    public void testClob() {
        String warranty = "My product warranty";

        Product product = new Product();
        product.setName("Mobile phone");
        product.setWarranty(warranty);

        persist(product);

        product = (Product) get(product, product.getId());
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
        persist(event);
    }

    @Test
    public void testFormula() {
        Account account = new Account();
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
        client.setName("Muhammed Topgul");
        persist(client);

        Account account001 = new Account();
        account001.setActive(true);
        account001.setAmount(1000.0);
        account001.setRate(0.9);
        account001.setCredit(12.00);
        account001.setType(AccountType.CREDIT);
        account001.setClient(client);
        persist(account001);

        Account account002 = new Account();
        account002.setActive(true);
        account002.setAmount(2000.0);
        account002.setRate(0.15);
        account002.setCredit(9.0);
        account002.setType(AccountType.DEBIT);
        account002.setClient(client);

        persist(account002);

        // read
        client = (Client) get(client, client.getId());
        assertEquals(1, client.getCreditAccounts().size());
        assertEquals(1, client.getDebitAccounts().size());
    }

    @Test
    public void testFilter() {
        Client client = new Client();
        client.setName("Muhammed Topgul");
        persist(client);

        Account account001 = new Account();
        account001.setActive(true);
        account001.setAmount(1000.0);
        account001.setRate(0.9);
        account001.setCredit(12.00);
        account001.setType(AccountType.CREDIT);
        account001.setClient(client);
        persist(account001);

        Account account002 = new Account();
        account002.setActive(false);
        account002.setAmount(2000.0);
        account002.setRate(0.15);
        account002.setCredit(9.0);
        account002.setType(AccountType.DEBIT);
        account002.setClient(client);

        persist(account002);

        Session session = getSession();

        // activate filter
        session.unwrap(Session.class);
        Filter filter = session.enableFilter("activeAccount");
        filter.setParameter("active", true);

        // class level filter
        List<Account> accounts = session
                .createQuery("select a from Account a", Account.class)
                .getResultList();

        assertEquals(1, accounts.size());

        // field level filter
        client = session.get(Client.class, client.getId());

        assertEquals(1, client.getAccounts().size());
    }

    @Test
    public void testSqlDelete() throws InterruptedException {
        User user = new User();
        user.setUsername("muhammed-topgul");
        user.setActive(true);
        user.setDeleted(false);

        persist(user);

        // Thread.sleep(5000);

        Session session = getSession();
        Transaction transaction = getTransaction(session);
        user = session.get(User.class, user.getId());

        session.remove(user);

        transactionCommit(transaction);
        session.close();
    }

    @Test
    public void testTarget() {
        City ankara = new City();
        ankara.setName("Ankara");
        ankara.setCoordinates(new GPS(46.77120, 23.62360));

        persist(ankara);
    }

    @Test(expected = PersistenceException.class)
    public void testNaturalId() {
        Country country = new Country();
        country.setName("Turkey");
        persist(country);

        country = new Country();
        country.setName("Turkey");
        persist(country);
    }

    @Test
    public void testEmbeddableManyToOne() {
        Country country = new Country();
        country.setName("Turkey");
        persist(country);

        Publisher publisher = new Publisher();
        publisher.setCountry(country);
        publisher.setName("Apress");

        Book book = new Book();
        book.setAuthor("Muhammed");
        book.setTitle("How to Java 17");
        book.setPublisher(publisher);
        persist(book);

        publisher = new Publisher();
        publisher.setCountry(country);
        publisher.setName("Deitel&Deitel");

        book = new Book();
        book.setAuthor("John");
        book.setTitle("How to Hibernate");
        book.setPublisher(publisher);
        persist(book);
    }

    @Before
    public void afterAll() {
        CurrentUser.INSTANCE.logOut();
    }
}
