package org.example.entity;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "pasword")
    private String pasword;
    @Basic
    @Column(name = "acountList_id")
    private int acountListId;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<AccountlistEntity> accountlistsById;
    public UserEntity(){
        System.out.println("build UserEntity");
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public int getAcountListId() {
        return acountListId;
    }

    public void setAcountListId(int acountListId) {
        this.acountListId = acountListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && acountListId == that.acountListId && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(login, that.login) && Objects.equals(pasword, that.pasword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, pasword, acountListId);
    }

    public Collection<AccountlistEntity> getAccountlistsById() {
        return accountlistsById;
    }

    public void setAccountlistsById(Collection<AccountlistEntity> accountlistsById) {
        this.accountlistsById = accountlistsById;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", pasword='" + pasword + '\'' +
                ", acountListId=" + acountListId +
                ", email='"+email+'\''+
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
