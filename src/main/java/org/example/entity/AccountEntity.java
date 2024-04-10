package org.example.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "bank", catalog = "")
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
    @Column(name = "accountNumber")
    private String accountNumber;
    @Basic
    @Column(name = "accountType")
    private String accountType;
    @Basic
    @Column(name = "balance")
    private double balance;
    @OneToMany(mappedBy = "accountByAccountId")
    private Collection<AccountlistEntity> accountlistsById;
    @OneToMany(mappedBy = "accountByAccountId")
    private Collection<TransactionlistEntity> transactionlistsById;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id && idUser == that.idUser && Double.compare(balance, that.balance) == 0 && Objects.equals(accountName, that.accountName) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, accountName, accountNumber, accountType, balance);
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
