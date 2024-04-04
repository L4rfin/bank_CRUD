package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.entity.AccountEntity;
import org.example.entity.UserEntity;

import java.io.IOException;


public class ControllerTransferView {
    @FXML
    TextField amountField;
    @FXML
    TextField transactionNumberField;
    @FXML
    TextField transactionNameField;
    @FXML
    Label accountName;
    @FXML
    Label accountNumber;
    @FXML
    Label accountBalance;
    private String transactionAccountNumber;
    private String TransferName;
    private String TransferSum;
    private AccountEntity account;
    private UserEntity user;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void onStart() {
        accountName.setText(account.getAccountName());
        accountNumber.setText(String.valueOf(account.getAccountNumber()));
        accountBalance.setText(String.valueOf(account.getBalance()));
    }

    public void updateACNumber() {
        if (onlyNumber(transactionNumberField.getText())) {
            setTransactionAccountNumber(transactionNumberField.getText());
        } else {
            System.out.println("transfer number cane be only a number");
        }

    }

    public void buttonReturnToHomepage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/homepage-view.fxml"));
        root = loader.load();
        ControllerHomepageView homepageViewController = loader.getController();
        homepageViewController.setUser(user);
        homepageViewController.onStart();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateACTransfer() {
        if (onlyNumber(amountField.getText())) {
            setTransferSum(amountField.getText());
        } else {
            System.out.println("transfer sum cane be only a number");
        }
    }

    public void updateTransferName() {
        if (!transactionNameField.getText().matches(".*\\W.*")) {
            setTransferName(transactionNameField.getText());
        } else {
            System.out.println("transfer name need to be fill and can't contain a special character");
        }

    }

    public void tryToProcessTransaction() {
        if (transactionDateValidator()) {
            System.out.println("transaction valid");
            //try to process transaction
        } else {
            System.out.println("incorrect date of transaction");
        }

    }

    public boolean transactionDateValidator() {
        if (getTransferName().isEmpty()) {
            System.out.println("TransferName empty");
            return false;
        }
        if (Integer.parseInt(getTransferSum()) <= 0) {
            System.out.println("Transfer sum can't be smaller or equal to 0");
            return false;
        }
        if (account.getBalance() < Integer.parseInt(getTransferSum())) {
            System.out.println("account balance is smaller then transfer sum");
            return false;
        }
        if (Integer.parseInt(getTransactionAccountNumber()) <= 0) {
            System.out.println("incorrect account number");
            return false;
        }
        return true;
    }


    public boolean onlyNumber(String value) {
        return value.matches("\\d*");
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getTransactionAccountNumber() {
        return transactionAccountNumber;
    }

    public void setTransactionAccountNumber(String transactionAccountNumber) {
        this.transactionAccountNumber = transactionAccountNumber;
    }

    public String getTransferName() {
        return TransferName;
    }

    public void setTransferName(String transferName) {
        TransferName = transferName;
    }

    public String getTransferSum() {
        return TransferSum;
    }

    public void setTransferSum(String transferSum) {
        TransferSum = transferSum;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
