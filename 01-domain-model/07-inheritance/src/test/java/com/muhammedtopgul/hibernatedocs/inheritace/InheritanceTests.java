package com.muhammedtopgul.hibernatedocs.inheritace;

import com.muhammedtopgul.hibernatedocs.inheritace.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.inheritace.entity.*;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.sessionClose;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author muhammed-topgul created at 07/10/2021 13:00
 */

public class InheritanceTests {

    @Before
    public void beforeAll() {
        HibernateUtil.setConfig(new HibernateConfig());
    }

    @Test
    public void testHibernateSetup() {
        Session session = getSession();

        transactionCommit(getTransaction(session));

        sessionClose(session);
        new HibernateConfig().getSessionFactory().close();
    }

    @Test
    public void testTableInheritanceQuery() {
        DebitAccount debitAccount = new DebitAccount();
        debitAccount.setBalance(new BigDecimal(100));
        debitAccount.setBalance(new BigDecimal(200));
        debitAccount.setInterestRate(new BigDecimal(0.2));
        debitAccount.setOverdraftFee(new BigDecimal(1500));
        persist(debitAccount);

        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setBalance(new BigDecimal(100));
        creditAccount.setBalance(new BigDecimal(300));
        creditAccount.setInterestRate(new BigDecimal(0.2));
        creditAccount.setCreditLimit(new BigDecimal(1500));
        persist(creditAccount);

        Session session = getSession();

        List<Account> accounts = session
                .createQuery("select a from Account a")
                .getResultList();

        accounts.forEach(account -> System.out.println(account.getBalance()));
    }

    @Test
    public void testExplicitPolymorphism() {
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("Vlad Mihalcea");
        book.setTitle("High-Performance Java Persistence");
        persist(book);

        Blog blog = new Blog();
        blog.setId(1L);
        blog.setSite("vladmihalcea.com");
        persist(blog);

        Session session = getSession();

        List<DomainModelEntity> accounts = session
                .createQuery(
                        "select e from com.muhammedtopgul.hibernatedocs.inheritace.entity.DomainModelEntity e" )
                .getResultList();

        assertEquals(1, accounts.size());
        assertTrue( accounts.get( 0 ) instanceof Book );
    }
}
