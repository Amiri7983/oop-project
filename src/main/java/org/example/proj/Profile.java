package org.example.proj;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Main;
import logic.User;

import java.net.URL;

import static logic.Control.psf;
import static logic.Main.users;

public class Profile extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Profile.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("menu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
        anchorPane.getChildren().add(ing);
        Label name = new Label(" ");
        name.setFont(new Font("Arial", 20));
        name.setTextFill(Color.WHITE);
        name.setLayoutX(180);
        name.setLayoutY(120);
        anchorPane.getChildren().add(name);
        name.setText("USERNAME : " + a.username + "\n\nPASSWORD : "+ a.password + "\n\nNICKNAME : " + a.nickname +"\n\nEMAIL : " + a.email);
        Button log = new Button("PASSWORD");
        log.setPrefHeight(30);
        log.setPrefWidth(96);
        log.setLayoutX(35);
        log.setLayoutY(340);
        log.setStyle("-fx-background-color: #263de8; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(log);
        log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Cpass c = new Cpass();
                    c.start(Profile.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button x = new Button("EMAIL");
        x.setPrefHeight(26);
        x.setPrefWidth(96);
        x.setLayoutX(141);
        x.setLayoutY(340);
        x.setStyle("-fx-background-color: #14856d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(x);
        x.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Cemail ae = new Cemail();
                    ae.start(Profile.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button y = new Button("NICKNAME");
        y.setPrefHeight(26);
        y.setPrefWidth(96);
        y.setLayoutX(247);
        y.setLayoutY(340);
        y.setStyle("-fx-background-color: #6a7703; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(y);
        y.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Cnickn acc = new Cnickn();
                    acc.start(Profile.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button z = new Button("USERNAME");
        z.setPrefHeight(26);
        z.setPrefWidth(96);
        z.setLayoutX(353);
        z.setLayoutY(340);
        z.setStyle("-fx-background-color: #9815a2; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(z);
        z.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Cusern aacc = new Cusern();
                    aacc.start(Profile.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button back = new Button("EXIT");
        back.setPrefHeight(26);
        back.setPrefWidth(64);
        back.setLayoutX(528);
        back.setLayoutY(340);
        back.setStyle("-fx-background-color: #d71c1c; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(back);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Menu m = new Menu();
                    m.start(Profile.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("CITY WARS : TOKYO");
        stage.show();
    }
}
