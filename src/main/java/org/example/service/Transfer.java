package org.example.service;

import jakarta.persistence.Query;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.example.entity.AccountEntity;
import org.example.entity.TransactionlistEntity;
import org.example.entity.TransactionsEntity;
import org.example.service.manager.EntityManager;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicReference;

public class Transfer {
    AccountEntity userAccount;
    AccountEntity innerAccount;

    public Transfer() {
        EntityManager.setUp();
    }

    public boolean proceedTransaction(String title, double sum, double provision, String innerACNumber, AccountEntity userAccount) {
        this.userAccount = userAccount;
        if (!getRecipientAccount(innerACNumber)) {
            System.out.println("wrong account number");
            return false;
        }

        if (!sendTransfer(sum)) {
            System.out.println("not sent");
            return false;
        }
        if (!saveTransactionInfo(title, sum, provision)) {
            System.out.println("transfer sent but not info was save");
            return false;
        }
        return true;
    }

    public boolean getRecipientAccount(String accountNumber) {
        EntityManager.setUp();
        EntityManager.inTransaction(entityManager -> {
            String jpql = "SELECT a FROM AccountEntity a WHERE a.accountNumber = :accountNumber";
            innerAccount = entityManager.createQuery(jpql, AccountEntity.class)
                    .setParameter("accountNumber", accountNumber)
                    .getSingleResult();
        });
        return innerAccount.getId() != 0;
    }

    public boolean sendTransfer(double sum) {
        userAccount.setBalance(userAccount.getBalance() - sum);
        innerAccount.setBalance(innerAccount.getBalance() + sum);

        EntityManager.inTransaction(entityManager -> {
            entityManager.merge(userAccount);
            entityManager.merge(innerAccount);
        });

        return true;
    }

    public boolean saveTransactionInfo(String title, double sum, double provision) {
        TransactionsEntity transaction = new TransactionsEntity();
        TransactionlistEntity list = new TransactionlistEntity();
        transaction.setTransactionTitle(title);
        transaction.setAccountIds(userAccount.getId());
        transaction.setOutAccountId(userAccount.getId());
        transaction.setInAccountId(innerAccount.getId());
        transaction.setOutSum(sum);
        transaction.setInSum(sum - provision);
        transaction.setProvision(provision);
        transaction.setOperationDate(getGlobalTime());

        EntityManager.inTransaction(entityManager -> entityManager.persist(transaction));

        AtomicReference<TransactionsEntity> idOfTransaction = new AtomicReference<>();
        EntityManager.inTransaction(entityManager -> {
            String q = "SELECT t FROM TransactionsEntity t WHERE HOUR(operationDate) = :hour AND MINUTE(operationDate) = :minute AND t.inAccountId = :inAC AND t.outAccountId = :outAC";
            Query query = entityManager.createQuery(q);
            query.setParameter("hour", transaction.getOperationDate().getHours());
            query.setParameter("minute", transaction.getOperationDate().getMinutes());
            query.setParameter("inAC", transaction.getInAccountId());
            query.setParameter("outAC", transaction.getOutAccountId());
            idOfTransaction.set((TransactionsEntity) query.getSingleResult());
        });
        System.out.println(transaction.getOperationDate().getTime());

        list.setAccountByAccountId(userAccount);
        list.setTransactionsByTransactionId(idOfTransaction.get());

        EntityManager.inTransaction(entityManager ->
                entityManager.persist(list)
        );
        TransactionlistEntity list2 = new TransactionlistEntity();
        list2.setAccountByAccountId(innerAccount);
        list2.setTransactionsByTransactionId(idOfTransaction.get());
        EntityManager.inTransaction(entityManager -> entityManager.persist(list2));
        return true;
    }

    public Timestamp getGlobalTime() {
        NTPUDPClient client = new NTPUDPClient();
        long returnTime = 0;
        try {
            client.open();
            InetAddress address = InetAddress.getByName("pool.ntp.org");
            TimeInfo timeInfo = client.getTime(address);
            returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
            System.out.println(returnTime);
        } catch (Exception e) {
            System.out.println("time dont get pull");
        } finally {
            client.close();
        }

        return new Timestamp(returnTime);
    }
}
