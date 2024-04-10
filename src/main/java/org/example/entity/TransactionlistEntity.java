package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "transactionlist", schema = "bank", catalog = "")
public class TransactionlistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    private AccountEntity accountByAccountId;
    @ManyToOne
    @JoinColumn(name = "transactionId", referencedColumnName = "id", nullable = false)
    private TransactionsEntity transactionsByTransactionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionlistEntity that = (TransactionlistEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AccountEntity getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(AccountEntity accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    public TransactionsEntity getTransactionsByTransactionId() {
        return transactionsByTransactionId;
    }

    public void setTransactionsByTransactionId(TransactionsEntity transactionsByTransactionId) {
        this.transactionsByTransactionId = transactionsByTransactionId;
    }
}
