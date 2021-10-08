package com.muhammedtopgul.hibernatedocs.inheritace;

import com.muhammedtopgul.hibernatedocs.inheritace.config.HibernateConfig;
import com.muhammedtopgul.hibernatedocs.inheritace.entity.Account;
import com.muhammedtopgul.hibernatedocs.inheritace.entity.CreditAccount;
import com.muhammedtopgul.hibernatedocs.inheritace.entity.DebitAccount;
import com.muhammedtopgul.hibernatedocs.utility.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.*;
import static com.muhammedtopgul.hibernatedocs.utility.HibernateUtil.sessionClose;

/**
 * created by Muhammed Topgul on 07/10/2021 at 13:00
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
                .createQuery( "select a from Account a" )
                .getResultList();

        accounts.forEach(account -> System.out.println(account.getBalance()));
    }
}
