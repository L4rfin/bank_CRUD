package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "accountlist", schema = "bank", catalog = "")
public class AccountlistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    private AccountEntity accountByAccountId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private UsersEntity usersByUserId;

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
        AccountlistEntity that = (AccountlistEntity) o;
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

    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
