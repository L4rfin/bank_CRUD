package org.example.entity;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "account")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "accountName")
    private String accountName;
    @Basic
    @Column(name = "accountType")
    private String accountType;
    @Basic
    @Column(name = "balance")
    private double balance;
    @Basic
    @Column(name = "accountNumber")
    private int accountNumber;
    @OneToMany(mappedBy = "accountByAccountId")
    private Collection<AccountlistEntity> accountlistsById;
    @OneToMany(mappedBy = "accountByAccountNumber")
    private Collection<TransactionlistEntity> transactionlistsById;

    public AccountEntity(){
        System.out.println("build AccountEntity");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id && idUser == that.idUser && Double.compare(balance, that.balance) == 0 && accountNumber == that.accountNumber && Objects.equals(accountName, that.accountName) && Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, accountName, accountType, balance, accountNumber);
    }

    public Collection<AccountlistEntity> getAccountlistsById() {
        return accountlistsById;
    }

    public void setAccountlistsById(Collection<AccountlistEntity> accountlistsById) {
        this.accountlistsById = accountlistsById;
    }

    public Collection<TransactionlistEntity> getTransactionlistsById() {
        return transactionlistsById;
    }

    public void setTransactionlistsById(Collection<TransactionlistEntity> transactionlistsById) {
        this.transactionlistsById = transactionlistsById;
    }
}
