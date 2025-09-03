package org.example.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "bank", catalog = "")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String  name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "passwords")
    private String passwords;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "accountList_id")
    private int accountListId;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<AccountlistEntity> accountlistsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String  name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountListId() {
        return accountListId;
    }

    public void setAccountListId(int accountListId) {
        this.accountListId = accountListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id && accountListId == that.accountListId && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(username, that.username) && Objects.equals(login, that.login) && Objects.equals(passwords, that.passwords) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, username, login, passwords, email, accountListId);
    }

    public Collection<AccountlistEntity> getAccountlistsById() {
        return accountlistsById;
    }

    public void setAccountlistsById(Collection<AccountlistEntity> accountlistsById) {
        this.accountlistsById = accountlistsById;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                "email"+email+
                '}';
    }
}
