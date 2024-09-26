package org.example.service;

import org.example.entity.UsersEntity;
import org.example.service.manager.EntityManager;

public class CreateUser {
    public CreateUser() {
        EntityManager.setUp();
    }

    public boolean AddUser(UsersEntity newUser) {
        LoginUser loginUser = new LoginUser();
        if (!loginUser.LookForUserAndLoginUsingUsername(newUser.getLogin(), newUser.getPasswords()).isSuccess()) {
            newUser.setLogin(MakeHashCode(newUser.getLogin()));
            newUser.setPasswords(MakeHashCode(newUser.getPasswords()));
            System.out.println("creating from addUSer");
            EntityManager.inTransaction(entityManager -> {
                entityManager.persist(newUser);
            });
            return true;
        } else {
            System.out.println("user existing");
        }
        return false;
    }

    public String MakeHashCode(String s) {
        System.out.println(s);
        String hashInString = String.valueOf(s.hashCode());
        System.out.println(hashInString);
        return hashInString;
    }
}
