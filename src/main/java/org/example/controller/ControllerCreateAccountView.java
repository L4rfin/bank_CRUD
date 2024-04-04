package org.example.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;
import org.example.entity.UserEntity;
import org.example.service.CreateUser;

import java.util.Objects;

public class ControllerCreateAccountView {
    Tooltip tooltip = new Tooltip("""
            a login is valid when contains at least one:\s
            lowercase letter, uppercase letter,
            at least 3 characters long\s

            a password is correct when it contains at least one:\s
            lowercase letter, uppercase letter, special character and number\s
            is at least 12 characters long""");
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField userNameField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField repeatPasswordField;
    @FXML
    TextField emailField;
    @FXML
    Label nameLabel;
    @FXML
    Label surnameLabel;
    @FXML
    Label userNameLabel;
    @FXML
    Label passwordLabel;
    @FXML
    Label rePasswordLabel;
    @FXML
    Label emailLabel;
    @FXML
    Label info;


    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean rePassword;
    private String email;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void UpdateNameString() {
        if (nameField.getText().length() >= 3) {
            switch (NameAndSurnameValidator(nameField.getText())) {
                case 3:
                    nameLabel.setText("Name: number in not allow in name field");
                    nameLabel.setStyle("-fx-text-fill: red");
                    break;
                case 4:
                    nameLabel.setText("Name: special character is not allow in name field");
                    nameLabel.setStyle("-fx-text-fill: red");
                    break;
                default:
                    nameLabel.setText("Name");
                    nameLabel.setStyle("-fx-text-fill: Green");
                    setName(nameField.getText());
            }
        } else {
            nameLabel.setText("Name");
            nameLabel.setStyle("-fx-text-fill: black");
        }
    }


    public void UpdateSurnameString() {
        if (surnameField.getText().length() >= 3) {
            switch (NameAndSurnameValidator(surnameField.getText())) {
                case 3:
                    surnameLabel.setText("Surname: number in not allow in name field");
                    surnameLabel.setStyle("-fx-text-fill: red");
                    break;
                case 4:
                    surnameLabel.setText("Surname: special character is not allow in name field");
                    surnameLabel.setStyle("-fx-text-fill: red");
                    break;
                default:
                    surnameLabel.setText("Surname");
                    surnameLabel.setStyle("-fx-text-fill: Green");
                    setName(nameField.getText());
                    setSurname(surnameField.getText());
            }
        } else {
            surnameLabel.setText("Surname");
            surnameLabel.setStyle("-fx-text-fill: black");
        }
    }

    public void UpdateEmailString() {
        if (EmailValidatorEmp(emailField.getText())) {
            emailLabel.setText("Email: is not valid");
            emailLabel.setStyle("-fx-text-fill: red; -fx-font-weight: normal");
        } else {
            email = emailField.getText();
            emailLabel.setStyle("-fx-text-fill: Green;-fx-font-weight: bold");
        }
    }

    public void UpdateLoginString() {
        if (userNameField.getText().length() > 3) {


            switch (LoginValidator(userNameField.getText())) {
                case 1:
                    userNameLabel.setText("User name: need to has at least 1 small letter");
                    userNameLabel.setStyle("-fx-text-fill: red");
                    break;
                case 2:
                    userNameLabel.setText("User name: need to has at least 1 big letter");
                    userNameLabel.setStyle("-fx-text-fill: red");
                    break;
                default:
                    userNameLabel.setText("User name");
                    userNameLabel.setStyle("-fx-text-fill: Green");
                    setLogin(userNameField.getText());
            }
        } else {
            userNameLabel.setText("User name");
            userNameLabel.setStyle("-fx-text-fill: black");
        }

    }

    public void UpdatePasswordString() {
        if (passwordField.getText().length() >= 12) {
            switch (PasswordValidator(passwordField.getText())) {
                case 1:
                    passwordLabel.setText("Password : need to has at least 1 small letter");
                    passwordLabel.setStyle("-fx-text-fill: Red");
                    break;
                case 2:
                    passwordLabel.setText("Password : need to has at least 1 big letter");
                    passwordLabel.setStyle("-fx-text-fill: Red");
                    break;
                case 3:
                    passwordLabel.setText("Password : need to has at least 1 number");
                    passwordLabel.setStyle("-fx-text-fill: Red");
                    break;
                case 4:
                    passwordLabel.setText("Password : need to has at least 1 special character");
                    passwordLabel.setStyle("-fx-text-fill: Red");
                    break;
                default:
                    passwordLabel.setText("Password");
                    passwordLabel.setStyle(
                            "-fx-font-weight: bold;" +
                                    "-fx-text-fill: green");
                    setPassword(passwordField.getText());
            }

        } else {
            passwordLabel.setText("Password");
            passwordLabel.setStyle(
                    "-fx-font-weight: normal;" +
                            "-fx-text-fill: black");
        }

    }

    public void UpdateRePasswordString() {
        if (repeatPasswordField.getText().matches(password)) {
            rePasswordLabel.setText("Repeat password");
            rePasswordLabel.setStyle(
                    "-fx-font-weight: bold;" +
                            "-fx-text-fill: green");
            setRePassword(true);
        } else {
            rePasswordLabel.setText("Passwords ont matching");
            rePasswordLabel.setStyle(
                    "-fx-font-weight: bold;" +
                            "-fx-text-fill: red");
            setRePassword(false);
        }
    }

    public void CreateUserFromDate(Event event) {
        System.out.println("try to create");
        if (!name.isEmpty() && !surname.isEmpty() && !password.isEmpty() && !email.isEmpty() && !login.isEmpty()) {
            System.out.println("create from date");
            UserEntity newUser = new UserEntity();
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setPasword(password);
            newUser.setLogin(login);
            newUser.setEmail(email);
            System.out.printf(newUser.toString());
            CreateUser createUser = new CreateUser();
            if (createUser.AddUser(newUser)) {
                System.out.println("user created");
                try {
                    GoToLoginView(event);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
    }

    @FXML
    public void GoToLoginView(Event event) throws Exception {
        FXMLLoader loader = (FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login-view.fxml"))));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRePassword(Boolean rePassword) {
        this.rePassword = rePassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean getRePassword() {
        return rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void displayInfo(MouseEvent mouseEvent) {
        Tooltip.install(info, tooltip);
        tooltip.setStyle("-fx-text-fill: gray");
        tooltip.setFont(Font.font("Times New Roman", 15));
    }

    public int NameAndSurnameValidator(String s) {
        if (s.matches(".*\\d.*"))
            return 3;
        if (s.matches(".*\\W.*"))
            return 4;

        return 0;
    }

    public int LoginValidator(String s) {
        if (!s.matches(".*[a-z].*"))
            return 1;
        if (!s.matches(".*[A-Z].*"))
            return 2;

        return 0;
    }

    public int PasswordValidator(String s) {
        if (!s.matches(".*[a-z].*"))
            return 1;
        if (!s.matches(".*[A-Z].*"))
            return 2;
        if (!s.matches(".*\\d.*"))
            return 3;
        if (!s.matches(".*\\W.*"))
            return 4;

        return 0;
    }

    public boolean EmailValidatorEmp(String s) {
        if (EmailValidator.getInstance().isValid(s)) {
            System.out.println(s + " not valid");
            return false;
        }
        System.out.println(s + " valid");
        return true;
    }


}

