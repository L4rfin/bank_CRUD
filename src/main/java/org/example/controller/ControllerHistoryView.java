package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.entity.AccountEntity;
import org.example.entity.TransactionsEntity;
import org.example.entity.UsersEntity;
import org.example.service.AccountHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class ControllerHistoryView {
    @FXML
    Label accountNameLabel;
    @FXML
    Label accountTypeLabel;
    @FXML
    Label accountNumber;
    @FXML
    ScrollPane scrollPanel;
    @FXML
    VBox accountListPanel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<TransactionsEntity> transactionList;
    private AccountEntity account;
    private UsersEntity user;

    public void onStart() {
        accountNameLabel.setText(account.getAccountName());
        accountTypeLabel.setText(account.getAccountType());
        accountNumber.setText(String.valueOf(account.getAccountNumber()));

        AccountHistory accountHistory = new AccountHistory();
        this.transactionList = accountHistory.getAccountHistory(account.getId());

        if (!transactionList.isEmpty()) {
            HashSet<TransactionsEntity> set = new HashSet<>(transactionList);
            transactionList.clear();
            transactionList.addAll(set);
        }
        if (!transactionList.isEmpty()) {
            Collections.sort(transactionList, Comparator.comparing(TransactionsEntity::getOperationDate));
            transactionList.forEach(this::CreateTransactionHistory);
        } else {
            System.out.println("transaction empty");
        }
    }

    public void CreateTransactionHistory(TransactionsEntity transaction) {

        javafx.geometry.Insets margins = new Insets(10);
        Pane panel1 = new Pane();
        panel1.setStyle("-fx-background-color: blue");
        panel1.setMaxWidth(350);
        VBox mainColumn = new VBox();
        Separator separator = new Separator(Orientation.HORIZONTAL);
        Separator separator1 = new Separator(Orientation.HORIZONTAL);
        separator1.setPrefHeight(20);
        Label TransferTitle = new Label("transfer title: " + transaction.getTransactionTitle());
        Label accountIds = new Label("account id: " + transaction.getAccountIds());
        Label outAccountId = new Label("out account id: " + transaction.getOutAccountId());
        Label inAccountId = new Label("in account id: " + transaction.getInAccountId());
        Label outSum = new Label("sum out: " + transaction.getOutSum());
        Label inSum = new Label("sum in: " + transaction.getInSum());
        Label provision = new Label("provision : " + transaction.getProvision());
        Label operationDate = new Label("operation date: " + transaction.getOperationDate());

        HBox firstRow = new HBox();
        HBox secondRow = new HBox();
        HBox thirdRow = new HBox();
        HBox fourthRow = new HBox();
        HBox fifthRow = new HBox();

        firstRow.getChildren().add(accountIds);
        firstRow.getChildren().add(TransferTitle);
        secondRow.getChildren().add(outAccountId);
        secondRow.getChildren().add(inAccountId);
        if (transaction.getInSum() > 0) {
            thirdRow.getChildren().add(inSum);
        }
        if (transaction.getOutSum() > 0) {
            thirdRow.getChildren().add(outSum);
        }
        fourthRow.getChildren().add(provision);
        fifthRow.getChildren().add(operationDate);

        HBox.setMargin(fifthRow, margins);
        HBox.setMargin(fourthRow, margins);
        HBox.setMargin(thirdRow, margins);
        HBox.setMargin(secondRow, margins);
        HBox.setMargin(firstRow, margins);
        mainColumn.getChildren().add(firstRow);
        mainColumn.getChildren().add(secondRow);
        mainColumn.getChildren().add(thirdRow);
        mainColumn.getChildren().add(fourthRow);
        mainColumn.getChildren().add(fifthRow);
        mainColumn.getChildren().add(separator);
        panel1.getChildren().add(mainColumn);
        accountListPanel.getChildren().add(panel1);
        accountListPanel.getChildren().add(separator1);
        accountListPanel.setPrefHeight(392 + accountListPanel.getHeight() + 500);
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

    public ArrayList<TransactionsEntity> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<TransactionsEntity> transactionList) {
        this.transactionList = transactionList;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}
