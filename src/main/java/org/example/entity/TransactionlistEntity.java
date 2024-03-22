package org.example.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactionlist")
public class TransactionlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionlistEntity that = (TransactionlistEntity) o;
        return id == that.id && Objects.equals(accountByAccountNumber, that.accountByAccountNumber) && Objects.equals(transactionByTransactionId, that.transactionByTransactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountByAccountNumber, transactionByTransactionId);
    }

    @ManyToOne
    @JoinColumn(name = "accountNumber", referencedColumnName = "id", nullable = false)
    private AccountEntity accountByAccountNumber;
    @ManyToOne
    @JoinColumn(name = "transactionId", referencedColumnName = "id", nullable = false)
    private TransactionEntity transactionByTransactionId;

    public TransactionlistEntity() {
        System.out.println("build TransactionlistEntity");
    }



    public int getId() {
        return id;
    }
    public AccountEntity getAccountByAccountNumber() {
        return accountByAccountNumber;
    }

    public void setAccountByAccountNumber(AccountEntity accountByAccountNumber) {
        this.accountByAccountNumber = accountByAccountNumber;
    }

    public TransactionEntity getTransactionByTransactionId() {
        return transactionByTransactionId;
    }

    public void setTransactionByTransactionId(TransactionEntity transactionByTransactionId) {
        this.transactionByTransactionId = transactionByTransactionId;
    }
}
