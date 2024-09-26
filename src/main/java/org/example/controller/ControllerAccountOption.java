package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.entity.AccountEntity;
import org.example.entity.UsersEntity;
import org.example.service.LoginUser;
import org.example.service.NewAccountUser;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerAccountOption {

    @FXML
    Label loadedUserName1;
    @FXML
    Label loadedUserSurname1;
    @FXML
    Label loadedUserUserName1;
    @FXML
    TextField newUserLogin;
    @FXML
    TextField newUserPassword;
    @FXML
    VBox newUserCardSpot;
    @FXML
    Label accountName;
    @FXML
    Label accountType;
    @FXML
    Label accountNumber;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private AccountEntity account;
    private UsersEntity loadUser;
    private ArrayList<UsersEntity> usersList;
    private UsersEntity searchUser;

    public void onStart() {
        accountName.setText(account.getAccountName());
        accountType.setText(account.getAccountType());
        accountNumber.setText(account.getAccountNumber());
        loadedUserName1.setText(loadUser.getName());
        loadedUserSurname1.setText(loadUser.getSurname());
        loadedUserUserName1.setText(loadUser.getUsername());
    }

    public void searchButtonPress(MouseEvent event) {
        if (!newUserPassword.getText().isEmpty() && !newUserLogin.getText().isEmpty()) {
            LoginUser newUser = new LoginUser();
//            this.searchUser = newUser.LookForUserAndLoginUsingUsername(newUserLogin.getText(), newUserPassword.getText());
        }
        if (searchUser.getId() != 0) {
            generateUserCard();
        } else generateEmptyUserCard();
    }
    public void addNewUserToAccount(){
        NewAccountUser newAccountUser = new NewAccountUser();
        if(newAccountUser.addUser(searchUser,account)){
            System.out.println("user added");
        }
    }
    public void generateEmptyUserCard() {

    }

    public void generateUserCard() {

    }


    public UsersEntity getLoadUser() {
        return loadUser;
    }

    public void setLoadUser(UsersEntity loadUser) {
        this.loadUser = loadUser;
    }

    public ArrayList<UsersEntity> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UsersEntity> usersList) {
        this.usersList = usersList;
    }

    public UsersEntity getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(UsersEntity searchUser) {
        this.searchUser = searchUser;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public void returnHome(MouseEvent event) throws IOException {
        System.out.println("try to go homepage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/homepage-view.fxml"));
        root = loader.load();
        ControllerHomepageView homepageViewController = loader.getController();
        homepageViewController.setUser(loadUser);
        homepageViewController.onStart();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
