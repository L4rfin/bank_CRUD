package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdk.jfr.Event;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main extends Application {
    static SessionFactory sessionFactory;

    static void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory =
                    new MetadataSources(registry)
                            .addAnnotatedClass(UserEntity.class)
                            .addAnnotatedClass(AccountlistEntity.class)
                            .addAnnotatedClass(AccountEntity.class)
                            .addAnnotatedClass(TransactionlistEntity.class)
                            .addAnnotatedClass(TransactionEntity.class)
                            .buildMetadata()
                            .buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    protected void tearDown() throws Exception{
        if (sessionFactory != null){
            sessionFactory.close();
        }
    }
    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("To jest nowe okno!");

        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Nowe Okno");

        primaryStage.show();
    }

    public static void main(String[] args) {
        //launch(args);
        setUp();
        UserEntity user = new UserEntity();
        user.setName("asdasd");
        user.setSurname("asdq1213");
        user.setLogin("asdad21");
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            session.persist(user);

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
