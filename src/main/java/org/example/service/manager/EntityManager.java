package org.example.service.manager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;


public class EntityManager {

    public static EntityManagerFactory entityManagerFactory;

    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bank");
    }
    public static void inTransaction(Consumer<jakarta.persistence.EntityManager> work) {
        jakarta.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }


}
