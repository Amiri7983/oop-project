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

public class Cusern extends Application {
    public static Stage stage;
    @FXML
    TextField username;
    static String user;
    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            user = newValue;
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        Cusern.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("changeun.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
        anchorPane.getChildren().add(ing);
        Button change = new Button("CHANGE");
        change.setPrefWidth(100);
        change.setPrefHeight(30);
        change.setLayoutX(250);
        change.setLayoutY(300);
        change.setStyle("-fx-background-color: #13c100; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(change);
        change.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    a.username =user;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("username changed successfully!");
                    alert.showAndWait();
                    Profile m = new Profile();
                    m.start(Cusern.stage);
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
                    m.start(Cusern.stage);
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
