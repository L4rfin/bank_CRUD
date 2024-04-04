package org.example.service;

import org.example.entity.UserEntity;

public class CreateUser {
    public CreateUser() {
        EntityManager.setUp();
    }

    public boolean AddUser(UserEntity newUser) {
        LoginUser loginUser = new LoginUser();
        if (loginUser.LookForUserAndLoginUsingUsername(newUser.getLogin(), newUser.getPasword()) == null) {
            newUser.setLogin(MakeHashCode(newUser.getLogin()));
            newUser.setPasword(MakeHashCode(newUser.getPasword()));
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
