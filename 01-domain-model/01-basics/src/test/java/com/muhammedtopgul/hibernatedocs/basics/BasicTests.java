package com.muhammedtopgul.hibernatedocs.basics;

import com.muhammedtopgul.hibernatedocs.basics.entity.*;
import com.muhammedtopgul.hibernatedocs.basics.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.GPS;
import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.Name;
import com.muhammedtopgul.hibernatedocs.basics.entity.embeddable.Publisher;
import com.muhammedtopgul.hibernatedocs.basics.enumeration.AccountType;
import com.muhammedtopgul.hibernatedocs.basics.enumeration.Gender;
import com.muhammedtopgul.hibernatedocs.basics.enumeration.PhoneType;
import com.muhammedtopgul.hibernatedocs.basics.user.CurrentUser;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;
import java.util.Date;
import java.util.List;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static org.junit.Assert.*;

/**
 * @author muhammed-topgul created at 13/09/2021 09:12
 */

public class BasicTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
        CurrentUser.INSTANCE.logIn("Muhammed");
    }

    @Test
    public void testHibernateSetup() {
        Session session = getSession();

        transactionCommit(getTransaction(session));

        sessionClose(session);
        new HibernateConfig().getSessionFactory().close();
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

        product = get(Product.class, product.getId());
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

        account = get(Account.class, account.getId());
        assertEquals(String.valueOf(account.getCredit() * account.getRate()), account.getInterest().toString());
    }

    @Test
    public void testWhereClause() {
        // write
        Client client = new Client();
        client.setFirstName("Muhammed");
        client.setLastName("Topgul");
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
        client = get(Client.class, client.getId());
        assertEquals(1, client.getCreditAccounts().size());
        assertEquals(1, client.getDebitAccounts().size());
    }

    @Test
    public void testFilter() {
        Client client = new Client();
        client.setFirstName("Muhammed");
        client.setLastName("Topgul");
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

    @Test
    public void testSessionScopeOfIdentity() {
        // write
        Book book1 = new Book();
        book1.setAuthor("Muhammed");
        book1.setTitle("How to Java 17");
        persist(book1);

        Book book2 = new Book();
        book2.setAuthor("Muhammed");
        book2.setTitle("How to Hibernate");
        persist(book2);

        // read
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        transactionCommit(transaction);

        book1 = session.get(Book.class, book1.getId());
        // returns same object from persistence context not from database
        book2 = session.get(Book.class, book1.getId());

        assertSame(book1, book2);
    }

    @Test
    public void testSessionScopeOfSet() {
        // write
        Book book1 = new Book();
        book1.setAuthor("Muhammed");
        book1.setTitle("How to Java 17");
        persist(book1);

        Book book2 = new Book();
        book2.setAuthor("Muhammed");
        book2.setTitle("How to Hibernate");
        persist(book2);

        Library library = new Library();
        library.setName("Public Libs");
        persist(library);

        // read
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        transactionCommit(transaction);

        library = session.get(Library.class, library.getId());

        book1 = session.get(Book.class, book1.getId());
        // returns same object from persistence context not from database
        book2 = session.get(Book.class, book1.getId());

        library.addBook(book1);
        library.addBook(book2);

        assertEquals(1, library.getBooks().size());

        // close session
        sessionClose(session);
        session = getSession();

        // returns from database not from persistence context
        book2 = session.get(Book.class, book1.getId());
        library.addBook(book2);

        assertEquals(2, library.getBooks().size());
    }

    @Test
    public void testMixedSession() {
        // write
        Book book1 = new Book();
        book1.setAuthor("Muhammed");
        book1.setTitle("How to Java 17");
        persist(book1);

        Book book2 = new Book();
        book2.setAuthor("Muhammed");
        book2.setTitle("How to Hibernate");
        persist(book2);

        // read
        book1 = get(Book.class, book1.getId());
        book2 = get(Book.class, book2.getId());

        assertNotSame(book1, book2);
    }

    @Test
    public void testSetsWithTransientEntities() {
        // write
        Library library = new Library();
        library.setName("Public Libs");
        persist(library);

        Book book1 = new Book();
        book1.setTitle("High-Performance Java Persistence");

        Book book2 = new Book();
        book2.setTitle("Java Persistence with Hibernate");

        assertNotSame(book1, book2);

        library.addBook(book1);
        library.addBook(book2);

        assertEquals(2, library.getBooks().size());
    }

    @Test
    public void testSubSelect() {
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        persist(client);

        Account account = new Account();
        account.setClient(client);
        account.setDescription("Checking account");
        persist(account);

        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setDescription("Salary");
        transaction.setCents(100 * 7000);
        persist(transaction);

        Session session = getSession();

        AccountSummary summary = session.createQuery(
                        "select s " +
                                "from AccountSummary s " +
                                "where s.id = :id", AccountSummary.class)
                .setParameter("id", account.getId())
                .getSingleResult();

        assertEquals("John Doe", summary.getClientName());
        assertEquals(100 * 7000, summary.getBalance());
        System.out.println(summary);
    }

    @After
    public void afterAll() {
        CurrentUser.INSTANCE.logOut();
    }
}
