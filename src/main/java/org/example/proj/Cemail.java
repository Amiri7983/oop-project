package org.example.proj;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Main;
import logic.User;

import java.net.URL;

import static logic.Main.users;

public class Cemail extends Application {
    public static Stage stage;
    @FXML
    TextField email;
    static String ev;
    @FXML
    public void initialize() {
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            ev = newValue;
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        Cemail.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("changeemail.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
        anchorPane.getChildren().add(ing);
        Button change1 = new Button("CHANGE");
        change1.setPrefWidth(100);
        change1.setPrefHeight(30);
        change1.setLayoutX(250);
        change1.setLayoutY(300);
        change1.setStyle("-fx-background-color: #13c100; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(change1);
        change1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    a.changemail(ev);
                    if (User.em == true) {
                        Profile x = new Profile();
                        x.start(Cemail.stage);
                    }
                    else{
                        Cemail y = new Cemail();
                        y.start(Cemail.stage);
                    }

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
                    Profile m = new Profile();
                    m.start(Cemail.stage);
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
