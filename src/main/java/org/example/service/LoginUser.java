package org.example.service;

import jakarta.persistence.Query;
import org.example.Result;
import org.example.entity.UsersEntity;
import org.example.service.manager.EntityManager;

import java.util.concurrent.atomic.AtomicReference;

public class LoginUser {
    public LoginUser(){
        EntityManager.setUp();
    }
    public Result<UsersEntity> LookForUserAndLoginUsingUsername(String userName, String password) {
        System.out.println("user name");
        String  userNamehc = MakeHashCode(userName);
        String passwordhc = MakeHashCode(password);
        AtomicReference<UsersEntity> user = new AtomicReference<>();
        try{EntityManager.inTransaction(entityManager -> {
            Query query = entityManager.createQuery("SELECT u FROM UsersEntity u WHERE u.login = :login AND u.passwords = :password");
            query.setParameter("login", userNamehc);
            query.setParameter("password", passwordhc);
            user.set((UsersEntity) query.getSingleResult());
        });
        }catch (Exception e){
            System.out.println("no user found");
            return Result.error(e.getMessage());
        }
        return Result.success(user.get());
    }
//    } public UsersEntity LookForUserAndLoginUsingUsername(String userName, String password) {
//        System.out.println("user name");
//        String  userNamehc = MakeHashCode(userName);
//        String passwordhc = MakeHashCode(password);
//        AtomicReference<UsersEntity> user = new AtomicReference<>();
//        try{EntityManager.inTransaction(entityManager -> {
//            Query query = entityManager.createQuery("SELECT u FROM UsersEntity u WHERE u.login = :login AND u.passwords = :password");
//            query.setParameter("login", userNamehc);
//            query.setParameter("password", passwordhc);
//            user.set((UsersEntity) query.getSingleResult());
//        });
//        }catch (Exception e){
//            System.out.println("no user found");
//            user.set(new UsersEntity());
//        }
//        return user.get();
//    }
    public UsersEntity LookForUserAndLoginUsingEmail(String email, String password) {
        System.out.println("email");
        String passwordhc = MakeHashCode(password);
        AtomicReference<UsersEntity> user = new AtomicReference<>();
        try{EntityManager.inTransaction(entityManager -> {
            Query query = entityManager.createQuery("SELECT u FROM UsersEntity u WHERE u.email = :email AND u.passwords = :password");
            query.setParameter("email", email);
            query.setParameter("password", passwordhc);
            user.set((UsersEntity) query.getSingleResult());
        });
        }catch (Exception e){
            System.out.println("no user found");
            user.set(new UsersEntity());
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
