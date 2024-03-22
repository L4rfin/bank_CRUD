package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.Main;
import org.example.entity.UserEntity;
import org.example.service.EntityManager;

import java.util.concurrent.atomic.AtomicReference;


public class ControllerLogin {
    @FXML
    Button button;
    @FXML
    Label labelEvent;

    public void event(){
        EntityManager.setUp();
        UserEntity user = new UserEntity();
        user.setName("AAAA");
        user.setSurname("BBBB");
        user.setLogin("CCCC");
        AtomicReference<String> anse = new AtomicReference<>("");
        EntityManager.inTransaction(entityManager -> {
            entityManager.persist(user);
        });
        labelEvent.setText(anse.get());
    }

}
