package org.example.service;

import jakarta.persistence.Query;
import org.example.entity.TransactionEntity;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class AccountHistory {
    public AccountHistory() {
        EntityManager.setUp();
    }

    public ArrayList<TransactionEntity> getAccountHistory(int accountIds) {
        AtomicReference<ArrayList<TransactionEntity>> list = new AtomicReference<>();
        try {
            EntityManager.inTransaction(entityManager -> {
                Query query = entityManager.createQuery("SELECT transaction FROM TransactionEntity transaction WHERE transaction.accountIds = :accountIds");
                query.setParameter("accountIds", accountIds);
                list.set((ArrayList<TransactionEntity>) query.getResultList());
            });
        } catch (Exception e) {
            System.out.println("exception");
            list.set(null);
        }
        return list.get();
    }
}
