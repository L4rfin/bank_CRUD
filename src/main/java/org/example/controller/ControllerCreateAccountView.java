package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.entity.AccountEntity;
import org.example.entity.UsersEntity;
import org.example.service.CreateAccount;

import java.io.IOException;
import java.util.Random;

public class ControllerCreateAccountView {

    @FXML
    TextField accountNameField;
    @FXML
    ComboBox<String> accountTypeComboBox;
    @FXML
    TextField accountNumberField;
    @FXML
    TextField accountBalanceField;
    private UsersEntity loadUser;
    private AccountEntity newAccount;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void onStart() {
        this.newAccount = new AccountEntity();
        accountTypeComboBox.getItems().addAll( "Savings account",
                                                    "Personal account",
                                                    "Business account",
                                                    "Student account",
                                                    "Junior account");

    }

    public boolean setData() {
        if (!accountNameField.getText().isEmpty()){
            newAccount.setAccountName(accountNameField.getText());
        }else return false;
        if (!accountTypeComboBox.getId().isEmpty()){
            newAccount.setAccountType(accountTypeComboBox.getSelectionModel().getSelectedItem());
        }else return false;
        if (!accountNumberField.getText().isEmpty()){
            newAccount.setAccountNumber(accountNumberField.getText());
        }else return false;
        newAccount.setIdUser(loadUser.getId());
        if (!accountBalanceField.getText().isEmpty()){
            newAccount.setBalance(Double.parseDouble(accountBalanceField.getText()));
        }else return false;
        return true;
    }

    public void setLoadUser(UsersEntity loadUser) {
        this.loadUser = loadUser;
    }

    public void buttonCreatePress() {
        if (setData()) {
            CreateAccount createAccount = new CreateAccount();
            createAccount.TryToCreateAccount(newAccount, loadUser);
        }
    }
    public void generateNewNumber(){
        Random random = new Random();
        StringBuilder newNumber = new StringBuilder();
        for (int i =0; i<7;i++){
            newNumber.append(random.nextInt(0, 9));
        }
        accountNumberField.setText(newNumber.toString());
    }
    public void buttonReturnToHomepage(MouseEvent event) throws IOException {
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
