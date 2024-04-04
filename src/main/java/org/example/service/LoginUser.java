package org.example.service;

import jakarta.persistence.Query;
import org.example.entity.UserEntity;
import java.util.concurrent.atomic.AtomicReference;

public class LoginUser {
    public LoginUser(){
        EntityManager.setUp();
    }
    public UserEntity LookForUserAndLoginUsingUsername(String userName, String password) {
        MakeHashCode(userName);
        MakeHashCode(password);
        AtomicReference<UserEntity> user = new AtomicReference<>();
        try{EntityManager.inTransaction(entityManager -> {
            Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.login = :login AND u.pasword = :password");
            query.setParameter("login", userName);
            query.setParameter("password", password);
            user.set((UserEntity) query.getSingleResult());
        });
        }catch (Exception e){
            System.out.println("exception");
            user.set(null);
        }
        return user.get();
    }
    public UserEntity LookForUserAndLoginUsingEmail(String email, String password) {
        MakeHashCode(password);
        AtomicReference<UserEntity> user = new AtomicReference<>();
        try{EntityManager.inTransaction(entityManager -> {
            Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email AND u.pasword = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            user.set((UserEntity) query.getSingleResult());
        });
        }catch (Exception e){
            System.out.println("exception");
            user.set(null);
        }
        return user.get();
    }
    public String MakeHashCode(String s){
        System.out.println(s);
        String hashInString = String.valueOf(s.hashCode());
        System.out.println(hashInString);
        return hashInString;
    }

}
