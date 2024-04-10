package org.example.service;

import jakarta.persistence.Query;
import org.example.entity.TransactionsEntity;
import org.example.service.manager.EntityManager;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class AccountHistory {
    public AccountHistory() {
        EntityManager.setUp();
    }

    public ArrayList<TransactionsEntity> getAccountHistory(int accountId) {
        AtomicReference<ArrayList<TransactionsEntity>> list = new AtomicReference<>();
        try {
            EntityManager.inTransaction(entityManager -> {
                String hql = "SELECT a FROM TransactionsEntity a " +
                        "JOIN TransactionlistEntity al ON a.id = al.transactionsByTransactionId.id " +
                        "WHERE al.accountByAccountId.id =: accountId";
                Query query = entityManager.createQuery(hql);
                query.setParameter("accountId", accountId);
                list.set((ArrayList<TransactionsEntity>) query.getResultList());
            });
        } catch (Exception e) {
            System.out.println("exception");
            list.set(null);
        }

        return list.get();
    }
}
