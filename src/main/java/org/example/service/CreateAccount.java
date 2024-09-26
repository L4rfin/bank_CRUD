package org.example.service;

import org.example.entity.AccountEntity;
import org.example.entity.AccountlistEntity;
import org.example.entity.UsersEntity;
import org.example.service.manager.EntityManager;

public class CreateAccount {

    public CreateAccount(){
        EntityManager.setUp();}

   public boolean TryToCreateAccount(AccountEntity account, UsersEntity users){
        try{
        EntityManager.inTransaction(entityManager -> {
            entityManager.persist(account);
        });
        }catch (Exception e){
            System.out.println("account not created");
            return false;
        }
        return AddToAccountList(account,users);
   }
   boolean AddToAccountList(AccountEntity account,UsersEntity users){
       AccountlistEntity accountList = new AccountlistEntity();
       accountList.setAccountByAccountId(account);
       accountList.setUsersByUserId(users);
        try {
            EntityManager.inTransaction(entityManager -> {
                entityManager.persist(accountList);
            });
        }catch (Exception e){
            System.out.println("not add to list");
            return false;
        }
        return true;
   }

}
