package org.example.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "accountIds")
    private int accountIds;
    @Basic
    @Column(name = "outAccountId")
    private int outAccountId;
    @Basic
    @Column(name = "inAccountId")
    private int inAccountId;
    @Basic
    @Column(name = "outSum")
    private double outSum;
    @Basic
    @Column(name = "inSum")
    private double inSum;
    @Basic
    @Column(name = "provision")
    private double provision;
    @Basic
    @Column(name = "operationDate")
    private Timestamp operationDate;
    @OneToMany(mappedBy = "transactionByTransactionId")
    private Collection<TransactionlistEntity> transactionlistsById;
    public TransactionEntity(){
        System.out.println("build TransactionEntity");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(int accountIds) {
        this.accountIds = accountIds;
    }

    public int getOutAccountId() {
        return outAccountId;
    }

    public void setOutAccountId(int outAccountId) {
        this.outAccountId = outAccountId;
    }

    public int getInAccountId() {
        return inAccountId;
    }

    public void setInAccountId(int inAccountId) {
        this.inAccountId = inAccountId;
    }

    public double getOutSum() {
        return outSum;
    }

    public void setOutSum(double outSum) {
        this.outSum = outSum;
    }

    public double getInSum() {
        return inSum;
    }

    public void setInSum(double inSum) {
        this.inSum = inSum;
    }

    public double getProvision() {
        return provision;
    }

    public void setProvision(double provision) {
        this.provision = provision;
    }

    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id == that.id && accountIds == that.accountIds && outAccountId == that.outAccountId && inAccountId == that.inAccountId && Double.compare(outSum, that.outSum) == 0 && Double.compare(inSum, that.inSum) == 0 && Double.compare(provision, that.provision) == 0 && Objects.equals(operationDate, that.operationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountIds, outAccountId, inAccountId, outSum, inSum, provision, operationDate);
    }

    public Collection<TransactionlistEntity> getTransactionlistsById() {
        return transactionlistsById;
    }

    public void setTransactionlistsById(Collection<TransactionlistEntity> transactionlistsById) {
        this.transactionlistsById = transactionlistsById;
    }
}
