package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.entity.AccountEntity;
import org.example.entity.UsersEntity;
import org.example.service.AccountListOfUserId;

import java.io.IOException;
import java.util.ArrayList;


public class ControllerHomepageView {
    @FXML
    Label clientName;
    @FXML
    Label clientSurname;
    @FXML
    Label clientId;
    private UsersEntity userLoaded;
    @FXML
    HBox accountListPanel;
    @FXML
    ScrollPane scrollPanel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<AccountEntity> accountList;

    public void onStart() {
        AccountListOfUserId accountListOfUserId = new AccountListOfUserId();
        setAccountList(accountListOfUserId.GetListOfAccount(userLoaded.getId()));
        accountList.forEach(accountEntity -> System.out.println(accountEntity.getId()));
        accountList.forEach(this::CreateAccountCard);
        CreateAccountCard();
        // setup user info
        clientId.setText(String.valueOf(userLoaded.getId()));
        clientName.setText(userLoaded.getName());
        clientSurname.setText(userLoaded.getSurname());

    }

    public void setUser(UsersEntity user) {
        this.userLoaded = user;
    }

    public void CreateAccountCard(AccountEntity entity) {
        Pane panel1 = new Pane();
        Separator cardSeparator = new Separator(Orientation.VERTICAL);
        Separator buttonSeparator = new Separator(Orientation.VERTICAL);
        Separator buttonSeparator1 = new Separator(Orientation.VERTICAL);
        Separator elementSeparator1 = new Separator(Orientation.VERTICAL);
        Separator elementSeparator2 = new Separator(Orientation.VERTICAL);
        Separator elementSeparator3 = new Separator(Orientation.VERTICAL);
        Separator elementSeparator4 = new Separator(Orientation.VERTICAL);
        Separator elementSeparator5 = new Separator(Orientation.VERTICAL);

        buttonSeparator.setPrefWidth(30);
        buttonSeparator1.setPrefWidth(30);
        buttonSeparator.setPrefSize(10, 10);
        buttonSeparator1.setPrefSize(10, 10);
        panel1.setPrefSize(500, 900);
        panel1.setId("accountPanel");

        Insets margins = new Insets(10);
        VBox mainBoxCard = new VBox();

        HBox firstRow = new HBox();
        HBox.setMargin(firstRow, margins);
        HBox secondRow = new HBox();
        HBox.setMargin(secondRow, margins);
        HBox thirdRow = new HBox();
        HBox.setMargin(thirdRow, margins);
        HBox fourthRow = new HBox();
        HBox.setMargin(fourthRow, margins);
        HBox fifthRow = new HBox();
        HBox.setMargin(fifthRow, margins);
        Label cardAccountName = new Label(entity.getAccountName());
        cardAccountName.setId("cardAccountName");
        Label cardAccountType = new Label(entity.getAccountType());
        cardAccountType.setId("cardAccountType");
        Label cardAccountNumber = new Label(String.valueOf(entity.getAccountNumber()));
        cardAccountNumber.setId("cardAccountNumber");
        Label cardAccountBalance = new Label(String.valueOf(entity.getBalance()));
        cardAccountBalance.setId("cardAccountBalance");
        Button accountHistory = new Button("History");
        accountHistory.setOnMouseClicked((event -> {
            try {
                buttonHistoryPres(event, entity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        Button makeTransfer = new Button("Transfer");
        makeTransfer.setOnMouseClicked((event -> {
            try {
                buttonTransferPres(event, entity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        Button accountOption = new Button("Option");
        accountOption.setOnMouseClicked(event -> {
            try {
                buttonAccountOption(event, entity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        firstRow.getChildren().add(cardAccountName);

        secondRow.getChildren().add(cardAccountNumber);

        thirdRow.getChildren().add(cardAccountBalance);

        fourthRow.getChildren().add(cardAccountType);

        fifthRow.getChildren().add(accountHistory);
        fifthRow.getChildren().add(buttonSeparator);
        fifthRow.getChildren().add(makeTransfer);
        fifthRow.getChildren().add(buttonSeparator1);
        fifthRow.getChildren().add(accountOption);

        mainBoxCard.getChildren().add(firstRow);
        mainBoxCard.getChildren().add(elementSeparator1);

        mainBoxCard.getChildren().add(secondRow);
        mainBoxCard.getChildren().add(elementSeparator2);

        mainBoxCard.getChildren().add(thirdRow);
        mainBoxCard.getChildren().add(elementSeparator3);

        mainBoxCard.getChildren().add(fourthRow);
        mainBoxCard.getChildren().add(elementSeparator4);

        mainBoxCard.getChildren().add(fifthRow);
        mainBoxCard.getChildren().add(elementSeparator5);

        panel1.getChildren().add(mainBoxCard);
        accountListPanel.getChildren().add(cardSeparator);
        accountListPanel.getChildren().add(panel1);
        accountListPanel.setPrefWidth(392 + accountListPanel.getWidth() + 900);

    }

    public void CreateAccountCard() {
        Pane panel1 = new Pane();
        Separator cardSeparator = new Separator(Orientation.VERTICAL);
        cardSeparator.setVisible(false);
        panel1.setPrefSize(500, 200);
        panel1.setStyle("-fx-background-color: lightblue;" +
                "-fx-background-radius: 12;");

        Button creatAccount = new Button("Create Account");
        creatAccount.onMouseClickedProperty().set(event -> {
            try {
                buttonCreateAccountPres(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        panel1.getChildren().add(creatAccount);
        accountListPanel.getChildren().add(cardSeparator);
        accountListPanel.getChildren().add(panel1);
        accountListPanel.setPrefWidth(392 + accountListPanel.getWidth() + 900);
    }

    public ArrayList<AccountEntity> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<AccountEntity> accountList) {
        this.accountList = accountList;
    }

    public void buttonHistoryPres(MouseEvent event, AccountEntity entity) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/history-view.fxml"));
        root = loader.load();
        ControllerHistoryView controller = loader.getController();
        controller.setAccount(entity);
        controller.onStart();
        controller.setUser(userLoaded);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void buttonTransferPres(MouseEvent event, AccountEntity entity) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/transfer-view.fxml"));
        root = loader.load();
        ControllerTransferView controller = loader.getController();
        controller.setAccount(entity);
        controller.onStart();
        controller.setUser(userLoaded);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonCreateAccountPres(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/crate-account-view.fxml"));
        root = loader.load();
        ControllerCreateAccountView controller = loader.getController();
        controller.onStart();
        controller.setLoadUser(userLoaded);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void buttonAccountOption(MouseEvent event, AccountEntity account) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/account-option-view.fxml"));
        root = loader.load();
        ControllerAccountOption controller = loader.getController();
        controller.setLoadUser(userLoaded);
        controller.setAccount(account);
        controller.onStart();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonLogout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

