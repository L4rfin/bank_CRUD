package org.example.service;

import org.example.entity.AccountEntity;
import org.example.entity.AccountlistEntity;
import org.example.entity.UsersEntity;
import org.example.service.manager.EntityManager;

public class NewAccountUser {
    LoginUser loginUser;
    public NewAccountUser() {
        EntityManager.setUp();
        loginUser = new LoginUser();
    }

    public boolean addUser(UsersEntity newUser, AccountEntity account){
            AccountlistEntity accountlist = new AccountlistEntity();
            accountlist.setUsersByUserId(newUser);
            accountlist.setAccountByAccountId(account);
            EntityManager.inTransaction(entityManager -> {
                entityManager.persist(accountlist);
            });
        return true;
    }

}
