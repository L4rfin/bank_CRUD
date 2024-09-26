package org.example.service;

import jakarta.persistence.Query;
import org.example.entity.AccountEntity;
import org.example.service.manager.EntityManager;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class AccountListOfUserId {
    public AccountListOfUserId() {
        EntityManager.setUp();
    }

    public ArrayList<AccountEntity> GetListOfAccount(int userId) {
        AtomicReference<ArrayList<AccountEntity>> list = new AtomicReference<>();
        try {
            EntityManager.inTransaction(entityManager -> {
                String hql = "SELECT a FROM AccountEntity a " +
                        "JOIN AccountlistEntity al ON a.id = al.accountByAccountId.id " +
                        "WHERE al.usersByUserId.id = :userId";
                Query query = entityManager.createQuery(hql);
                query.setParameter("userId", userId);
                list.set((ArrayList<AccountEntity>) query.getResultList());
            });
        } catch (Exception e) {
            System.out.println("exception");
            list.set(null);
        }
        return list.get();
    }

}
