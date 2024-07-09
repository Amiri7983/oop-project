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

public class Cpass extends Application {
    public static Stage stage;
    @FXML
    public TextField newp,oldp;
    static String n , o;
    @FXML
    public void initialize() {
        newp.textProperty().addListener((observable, oldValue, newValue) -> {
            n = newValue;
        });
        oldp.textProperty().addListener((observable, oldValue, newValue) -> {
            o = newValue;
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        Cpass.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("changepass.fxml");
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
                    if(o.equals(a.password)){
                        User.changepass(n);
                        Profile x = new Profile();
                        x.start(Cpass.stage);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("incorrect password!");
                        alert.showAndWait();
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
                    m.start(Cpass.stage);
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
