package org.example.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "transactions", schema = "bank", catalog = "")
public class TransactionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "transactionTitle")
    private String transactionTitle;
    @Basic
    @Column(name = "accountIds")
    private int accountIds;
    @Basic
    @Column(name = "inAccountId")
    private int inAccountId;
    @Basic
    @Column(name = "outAccountId")
    private int outAccountId;
    @Basic
    @Column(name = "inSum")
    private double inSum;
    @Basic
    @Column(name = "operationDate")
    private Timestamp operationDate;
    @Basic
    @Column(name = "outSum")
    private double outSum;
    @Basic
    @Column(name = "provision")
    private double provision;
    @OneToMany(mappedBy = "transactionsByTransactionId")
    private Collection<TransactionlistEntity> transactionlistsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public void setTransactionTitle(String transactionTitle) {
        this.transactionTitle = transactionTitle;
    }

    public int getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(int accountIds) {
        this.accountIds = accountIds;
    }

    public int getInAccountId() {
        return inAccountId;
    }

    public void setInAccountId(int inAccountId) {
        this.inAccountId = inAccountId;
    }

    public int getOutAccountId() {
        return outAccountId;
    }

    public void setOutAccountId(int outAccountId) {
        this.outAccountId = outAccountId;
    }

    public double getInSum() {
        return inSum;
    }

    public void setInSum(double inSum) {
        this.inSum = inSum;
    }

    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    public double getOutSum() {
        return outSum;
    }

    public void setOutSum(double outSum) {
        this.outSum = outSum;
    }

    public double getProvision() {
        return provision;
    }

    public void setProvision(double provision) {
        this.provision = provision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsEntity that = (TransactionsEntity) o;
        return id == that.id && accountIds == that.accountIds && inAccountId == that.inAccountId && outAccountId == that.outAccountId && Double.compare(inSum, that.inSum) == 0 && Double.compare(outSum, that.outSum) == 0 && Double.compare(provision, that.provision) == 0 && Objects.equals(transactionTitle, that.transactionTitle) && Objects.equals(operationDate, that.operationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionTitle, accountIds, inAccountId, outAccountId, inSum, operationDate, outSum, provision);
    }

    public Collection<TransactionlistEntity> getTransactionlistsById() {
        return transactionlistsById;
    }

    public void setTransactionlistsById(Collection<TransactionlistEntity> transactionlistsById) {
        this.transactionlistsById = transactionlistsById;
    }
}
