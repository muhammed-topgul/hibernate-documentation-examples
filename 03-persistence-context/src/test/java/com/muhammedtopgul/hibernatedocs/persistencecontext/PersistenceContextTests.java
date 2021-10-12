package com.muhammedtopgul.hibernatedocs.persistencecontext;

import com.muhammedtopgul.hibernatedocs.persistencecontext.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Author;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Book;
import com.muhammedtopgul.hibernatedocs.persistencecontext.entity.Person;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;

/**
 * created by Muhammed Topgul on 12/10/2021 at 14:16
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
        book.setAuthor(authorReference);
        persist(book);
    }
}
