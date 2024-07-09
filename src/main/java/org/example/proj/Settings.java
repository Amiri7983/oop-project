package org.example.proj;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;


public class Settings extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Settings.stage = stage;
        URL url = Start.class.getResource("setting.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(40);
        anchorPane.getChildren().add(ing);
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
                    m.start(Settings.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button music1 = new Button("MUSIC1");
        music1.setPrefHeight(26);
        music1.setPrefWidth(64);
        music1.setLayoutX(150);
        music1.setLayoutY(150);
        music1.setStyle("-fx-background-color: #14856d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(music1);
        music1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
//                    media = new Media("/music1.mp3");
//                    Start.mediaplayer = new MediaPlayer(media);
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("music1 is playing");
                    alert.showAndWait();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button music2 = new Button("MUSIC2");
        music2.setPrefHeight(26);
        music2.setPrefWidth(64);
        music2.setLayoutX(220);
        music2.setLayoutY(150);
        music2.setStyle("-fx-background-color: #14856d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(music2);
        music2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
//                    media = new Media("/music2.mp3");
//                    Start.mediaplayer = new MediaPlayer(media);
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("music2 is playing");
                    alert.showAndWait();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button music3 = new Button("MUSIC3");
        music3.setPrefHeight(26);
        music3.setPrefWidth(64);
        music3.setLayoutX(290);
        music3.setLayoutY(150);
        music3.setStyle("-fx-background-color: #14856d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(music3);
        music3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
//                    media = new Media("/music3.mp3");
//                    Start.mediaplayer = new MediaPlayer(media);
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("music3 is playing");
                    alert.showAndWait();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button backg = new Button("CHANGE");
        backg.setPrefHeight(26);
        backg.setPrefWidth(64);
        backg.setLayoutX(290);
        backg.setLayoutY(220);
        backg.setStyle("-fx-background-color: #054416; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(backg);
        backg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    //Menu.change();
                    Menu.x= true;
                    //Menu m = new Menu();
                    //m.start(Settings.stage);
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("background changed successfully!");
                    alert.showAndWait();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button d = new Button("DEFAULT");
        d.setPrefHeight(26);
        d.setPrefWidth(64);
        d.setLayoutX(220);
        d.setLayoutY(220);
        d.setStyle("-fx-background-color: #55545d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(d);
        d.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    //Menu.change();
                    Menu.x= false ;
                    //Menu m = new Menu();
                    //m.start(Settings.stage);
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("background changed successfully!");
                    alert.showAndWait();
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
