package com.muhammedtopgul.hibernatedocs.persistencecontext;

import com.muhammedtopgul.hibernatedocs.persistencecontext.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Author;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Book;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Person;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Phone;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static org.junit.Assert.*;

/**
 * @author muhammed-topgul created at 12/10/2021 14:16
 */

public class PersistenceContextTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testPersistAndDelete() {
        Person person = new Person();
        person.setName("Muhammed");
        persist(person);

        Session session = getSession();
        Transaction transaction = getTransaction(session);
        System.out.printf("Person is in %s state. %n", session.contains(person) ? "MANAGED" : "DETACH");
        session.remove(person);
        transaction.commit();
        session.close();
    }

    @Test
    public void testEntityReferenceWithoutLoadData() {
        Author author = new Author();
        author.setName("Muhammed");
        persist(author);

        Book book = new Book();
        book.setName("How to Java");
        book.setIsbn("A124-B456");

        Author authorReference = reference(Author.class, author.getId()); // will not load Author entity (lazy loading)
        System.out.println("--- Reference Loaded ---");
        System.out.println("Author Name: " + authorReference.getName());  // until reach any field of entity
        System.out.println("Class Name: " + authorReference.getClass().getName()); // proxy class because of lazy loading
        assertNotEquals(authorReference.getClass().getName(), author.getClass().getName());
        book.setAuthor(authorReference);
        persist(book);
    }

    @Test
    public void testEntityReferenceWithLoadData() {
        Author author = new Author();
        author.setName("Muhammed");
        persist(author);

        Book book = new Book();
        book.setName("How to Java");
        book.setIsbn("A124-B456");

        Author author1 = get(Author.class, author.getId()); // will load Author entity
        System.out.println("--- Entity Loaded ---");
        System.out.println("Class Name: " + author1.getClass().getName()); // no proxy class
        assertEquals(author.getClass().getName(), author1.getClass().getName());

        author1 = byId(Author.class, author.getId());
        System.out.println("--- Entity Loaded ---");
        System.out.println("Class Name: " + author1.getClass().getName()); // no proxy class
        assertEquals(author.getClass().getName(), author1.getClass().getName());
    }

    @Test
    public void testEntityLoadDataById() {
        Author author = new Author();
        author.setName("Muhammed");
        persist(author);

        Book book = new Book();
        book.setName("How to Java");
        book.setIsbn("A124-B456");

        Author author1 = byId(Author.class, author.getId());
        System.out.println("--- Entity Loaded ---");
        System.out.println("Class Name: " + author1.getClass().getName()); // no proxy class
        assertEquals(author.getClass().getName(), author1.getClass().getName());
    }

    @Test
    public void testEntityOptionalById() {
        Author author = new Author();
        author.setName("Muhammed");
        persist(author);

        Book book = new Book();
        book.setName("How to Java");
        book.setIsbn("A124-B456");

        Optional<Author> optionalAuthor = optionalById(Author.class, "nonexistid");
        if (optionalAuthor.isPresent()) {
            System.out.println("--- Entity Loaded ---");
            System.out.println("Class Name: " + optionalAuthor.getClass().getName()); // no proxy class
            assertEquals(optionalAuthor.getClass().getName(), optionalAuthor.getClass().getName());
        } else {
            System.out.println("Author is null");
        }
    }

    @Test
    public void testReattachingDetachedEntityUsingSaveOrUpdate() {
        Person person = new Person();
        person.setName("Muhammed");
        persist(person);

        Session session = getSession();
        Transaction transaction = getTransaction(session);
        person = session.byId(Person.class).load(person.getId());

        session.clear(); // clear the Session so the person entity becomes detached
        person.setName("Mr. John Doe");

        session.saveOrUpdate(person); // make detach to managed

        session.persist(person);
        transaction.commit();
    }

    @Test
    public void testMergeDetachedEntity() {
        Person person = new Person();
        person.setName("Muhammed");
        persist(person);

        Session session = getSession();
        Transaction transaction = getTransaction(session);
        person = session.byId(Person.class).load(person.getId());

        session.clear(); // clear the Session so the person entity becomes detached
        person.setName("Mr. John Doe");

        person = (Person) session.merge(person);

        session.persist(person);
        transaction.commit();
    }

    @Test
    public void testCascadeTypePersist() {
        Person person = new Person();
        person.setName("John Doe");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        person.addPhone(phone);

        persist(person);

        // assert
        assertEquals(person.getName(), get(Person.class, person.getId()).getName());
        assertEquals(phone.getNumber(), get(Phone.class, phone.getId()).getNumber());
    }

    @Test
    public void testCascadeTypeMerge() {
        // save
        Person person = new Person();
        person.setName("John Doe");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        person.addPhone(phone);

        persist(person);

        // get
        Session session = getSession();
        Transaction transaction = getTransaction(session);

        phone = session.get(Phone.class, phone.getId());
        person = phone.getPerson();

        person.setName("John Doe Jr.");
        phone.setNumber("987-654-3210");

        session.clear();

        // merge
        session.merge(phone);
        session.merge(person);
        transaction.commit();

        // assert
        assertEquals("John Doe Jr.", get(Person.class, person.getId()).getName());
        assertEquals("987-654-3210", get(Phone.class, phone.getId()).getNumber());
    }

    @Test
    public void testCascadeTypeRemove() {
        // save
        Person person = new Person();
        person.setName("John Doe");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        person.addPhone(phone);

        persist(person);

        // get
        Session session = getSession();
        Transaction transaction = getTransaction(session);
        person = session.get(Person.class, person.getId());

        // remove
        session.remove(person);
        transaction.commit();

        // assert
        assertNull(get(Person.class, person.getId()));
        assertNull(get(Phone.class, phone.getId()));
    }

    @Test
    public void testCascadeTypeDetach() {
        // save
        Person person = new Person();
        person.setName("John Doe");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        person.addPhone(phone);

        persist(person);

        // get
        Session session = getSession();
        Transaction transaction = getTransaction(session);
        person = session.get(Person.class, person.getId());
        phone = person.getPhones().get(0);

        // assert
        assertTrue(session.contains(person));
        assertTrue(session.contains(phone));

        // detach
        session.detach(person);

        // assert
        assertFalse(session.contains(person));
        assertFalse(session.contains(phone));
    }

    @Test
    public void testCascadeTypeRefresh() {
        Person person = new Person();
        person.setName("John Doe");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        person.addPhone(phone);

        persist(person);

        Session session = getSession();

        person = session.get(Person.class, person.getId());
        phone = person.getPhones().get(0);

        person.setName("John Doe Jr.");
        phone.setNumber("987-654-3210");

        session.refresh(person);

        assertEquals("John Doe", person.getName());
        assertEquals("123-456-7890", phone.getNumber());
    }

    @Test
    public void testOnDelete() {
        // save
        Person person = new Person();
        person.setName("John Doe");

        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        person.addPhone(phone);

        persist(person);

        // get
        Session session = getSession();
        Transaction transaction = getTransaction(session);
        person = session.get(Person.class, person.getId());

        // remove
        session.remove(person);
        transaction.commit();

        // assert
        assertNull(get(Person.class, person.getId()));
        assertNull(get(Phone.class, phone.getId()));
    }
}
