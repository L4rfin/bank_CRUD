package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;
import org.example.entity.UserEntity;
import org.example.service.LoginUser;

import java.io.IOException;
import java.util.Objects;

public class ControllerLoginView {
    @FXML
    Button buttonLog_in;
    @FXML
    Label loginLabel;
    @FXML
    TextField loginField;
    @FXML
    Label passwordLabel;
    @FXML
    PasswordField passwordField;
    @FXML
    Label info;
    @FXML
    Label createAccountLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String login = "";
    private String password = "";
    private String email = "";

    Tooltip tooltip = new Tooltip("a login is valid when contains at least one: \n" +
            "lowercase letter, uppercase letter,\n" +
            " at least 3 characters long \n\n" +
            "a password is correct when it contains at least one: \n" +
            "lowercase letter, uppercase letter, special character and number \n" +
            "is at least 12 characters long");

    public void getUserInputLogin() {
        System.out.println(email);
        System.out.println(password);
        System.out.println(login);
        if (loginField.getText().length() > 3) {

            if (emailValidator(loginField.getText())) {
                System.out.println("login as email");
                updateLoginInputAsEmail(loginField.getText());
            }
            if(!emailValidator(loginField.getText())) {
                System.out.println("login as username");
                updateLoginInputAsUsername(loginField.getText());
            }
        }
    }


    public void getUserInputPassword() {
        if (passwordField.getText().length() >= 12) {
            if (!password.equals(passwordField.getText())) {
                setPassword(passwordField.getText());
                if (stringPasswordValidator(password)) {
                    passwordLabel.setStyle("-fx-text-fill: green");
                    passwordLabel.setStyle("-fx-font-weight: bold");
                } else {
                    passwordLabel.setText("Password: not valid");
                    passwordLabel.setStyle("-fx-text-fill: red");
                    passwordLabel.setStyle("-fx-font-weight: normal");
                }
            }

        }
    }

    public void updateLoginInputAsEmail(String email) {
            setEmail(email);
            setLogin(null);
        loginLabel.setText("User name or email");
        loginLabel.setStyle("-fx-text-fill: green");
        loginLabel.setStyle("-fx-font-weight: bold");
    }

    public void updateLoginInputAsUsername(String login) {
        if (stringLoginValidator(login)) {
            setLogin(login);
            setEmail(null);
            loginLabel.setText("User name or email");
            loginLabel.setStyle("-fx-text-fill: green");
            loginLabel.setStyle("-fx-font-weight: bold");
        } else {
            loginLabel.setText("User name or email : not valid");
            loginLabel.setStyle("-fx-text-fill: red");
            loginLabel.setStyle("-fx-font-weight: normal");
        }
    }

    public void loginUsingEmail(MouseEvent event)throws IOException {
        if (email != null && password != null) {
            LoginUser tryToLogin = new LoginUser();
            UserEntity user = tryToLogin.LookForUserAndLoginUsingEmail(email, password);
            System.out.printf(user.toString());
            if ((user.getId() > 0)) {
                System.out.printf(user.toString());
                GoToHomepage(event, user);

            } else {
                passwordLabel.setText("Password or user name is incorrect");
                passwordLabel.setStyle("-fx-text-fill: Red");

                loginLabel.setText("User Name or password is incorrect");
                loginLabel.setStyle("-fx-text-fill: Red");
            }
        }
    }

    public void loginUsingUsername(MouseEvent event) throws IOException {

        LoginUser tryToLogin = new LoginUser();
        UserEntity user = tryToLogin.LookForUserAndLoginUsingUsername(login, password);
        System.out.printf(user.toString());
        if ((user.getId() > 0)) {
            System.out.printf(user.toString());
            GoToHomepage(event, user);
        }

    }

    public boolean stringLoginValidator(String login) {
        if (!login.matches(".*[a-z].*"))
            return false;
        if (login.matches(".*\\W.*"))
            return false;
        System.out.println("login valid");
        return true;
    }

    public boolean emailValidator(String email) {
        System.out.println("email: " + email);
        System.out.println( EmailValidator.getInstance().isValid(email));
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean stringPasswordValidator(String password) {
        if (!password.matches(".*[A-Z].*"))
            return false;
        if (!password.matches(".*[a-z].*"))
            return false;
        if (!password.matches(".*\\d.*"))
            return false;
        if (!password.matches(".*\\W.*"))
            return false;
        System.out.println("password valid");
        return true;
    }

    public void displayInfo() {

        Tooltip.install(info, tooltip);
        tooltip.setStyle("-fx-text-fill: gray");
        tooltip.setFont(Font.font("Times New Roman", 15));
    }

    public void ButtonTryToLogin(MouseEvent event) throws Exception {
        if (login != null && password != null) {
            loginUsingUsername(event);
        }
        if (email != null && password !=null){
            loginUsingEmail(event);
        }
        else {
            passwordLabel.setText("Password or user name is incorrect");
            passwordLabel.setStyle("-fx-text-fill: Red");

            loginLabel.setText("User Name or password is incorrect");
            loginLabel.setStyle("-fx-text-fill: Red");
        }
    }
    

    public void MakeCABold() {
        createAccountLabel.setStyle(
                "-fx-text-fill: blue;" +
                        "-fx-font-weight: bold");
    }

    public void MakeCASlim() {
        createAccountLabel.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-font-weight: normal");
    }

    @FXML
    public void GoToCreateAccount(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/create-account-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void GoToHomepage(MouseEvent event, UserEntity user) throws IOException {
        System.out.println("try to go homepage");
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


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
