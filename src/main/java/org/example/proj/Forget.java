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

public class Forget extends Application{
    public  static Stage stage;
    @FXML
    static String usernamet,quest,num;
    @FXML
    public TextField unt,sqf,number;
    @FXML
    public void initialize() {
        unt.textProperty().addListener((observable, oldValue, newValue) -> {
            usernamet = newValue;
        });
        sqf.textProperty().addListener((observable, oldValue, newValue) -> {
            quest = newValue;
        });
        number.textProperty().addListener((observable, oldValue, newValue) -> {
            num = newValue;
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        Forget.stage = stage;
        URL url = Start.class.getResource("forget.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(40);
        anchorPane.getChildren().add(ing);
        Button log = new Button("DONE");
        log.setPrefHeight(26);
        log.setPrefWidth(74);
        log.setLayoutX(258);
        log.setLayoutY(315);
        log.setStyle("-fx-background-color: #00C135; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(log);
        log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).username.equals(usernamet)) {
                            if(users.get(i).answer1.equals(quest)){
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setContentText("your password: " + users.get(i).password);
                                alert.showAndWait();
                            }
                            else{
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("incorrect answer!");
                                alert.showAndWait();
                                Forget f = new Forget();
                                f.start(Forget.stage);
                            }
                        }
                    }
                    Login m = new Login();
                    m.start(Forget.stage);
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
