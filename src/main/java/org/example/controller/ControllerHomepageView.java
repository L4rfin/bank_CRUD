package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.entity.AccountEntity;
import org.example.entity.UserEntity;
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
    private UserEntity userLoaded;
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

        // setup user info
        clientId.setText(String.valueOf(userLoaded.getId()));
        clientName.setText(userLoaded.getName());
        clientSurname.setText(userLoaded.getSurname());

    }

    public void setUser(UserEntity user) {
        this.userLoaded = user;
    }

    public void CreateAccountCard(AccountEntity entity) {
        Pane panel1 = new Pane();
        Separator cardSeparator = new Separator(Orientation.VERTICAL);
        Separator buttonSeparator = new Separator(Orientation.VERTICAL);
        cardSeparator.setVisible(false);
        buttonSeparator.setVisible(false);
        buttonSeparator.setPrefWidth(120);
        buttonSeparator.setPrefSize(10, 10);
        panel1.setPrefSize(500, 200);
        panel1.setStyle("-fx-background-color: lightblue;" +
                "-fx-background-radius: 12;");
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

        Label accountName = new Label(entity.getAccountName());
        accountName.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 18");
        accountName.setWrapText(true);
        accountName.setTextOverrun(OverrunStyle.CENTER_WORD_ELLIPSIS);

        Label accountType = new Label(entity.getAccountType());
        accountType.setStyle("-fx-text-fill: grey;" +
                "-fx-font-size: 8;");

        Label accountNumber = new Label(String.valueOf(entity.getAccountNumber()));
        accountNumber.setStyle("-fx-text-fill: gray;" +
                "-fx-font-size: 8;");
        Label accountBalance = new Label(String.valueOf(entity.getBalance()));
        if (entity.getBalance() > 0) {
            accountBalance.setStyle("-fx-text-fill: green;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 20;");
        } else {
            accountBalance.setStyle("-fx-text-fill: red;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 20");
        }
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

        firstRow.getChildren().add(accountName);
        secondRow.getChildren().add(accountNumber);
        thirdRow.getChildren().add(accountBalance);
        fourthRow.getChildren().add(accountType);

        fifthRow.getChildren().add(accountHistory);
        fifthRow.getChildren().add(buttonSeparator);
        fifthRow.getChildren().add(makeTransfer);


        mainBoxCard.getChildren().add(firstRow);
        mainBoxCard.getChildren().add(secondRow);
        mainBoxCard.getChildren().add(thirdRow);
        mainBoxCard.getChildren().add(fourthRow);
        mainBoxCard.getChildren().add(fifthRow);

        panel1.getChildren().add(mainBoxCard);
        accountListPanel.getChildren().add(cardSeparator);
        accountListPanel.getChildren().add(panel1);
        accountListPanel.setPrefWidth(392 + accountListPanel.getWidth() + 500);

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

    public void buttonLogout(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

