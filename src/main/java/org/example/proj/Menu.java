package org.example.proj;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Main;
import logic.User;

import java.io.IOException;
import java.net.URL;

import static logic.Main.users;

public class Menu extends Application {
    public static Stage stage;
    public static boolean x = false;
    public static AnchorPane anchorPane  = new AnchorPane();
    public static URL url = Start.class.getResource("menu.fxml");
    public static URL url2 = Start.class.getResource("menu2.fxml");
    @Override
    public void start(Stage stage) throws Exception {
        Menu.stage = stage;
        User a = users.get(Main.index);
        //URL url = Start.class.getResource("menu.fxml");
        //AnchorPane anchorPane = FXMLLoader.load(url);
        change(x);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(100);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
        anchorPane.getChildren().add(ing);
        Button start = new Button("START");
        start.setPrefHeight(30);
        start.setPrefWidth(150);
        start.setLayoutX(230);
        start.setLayoutY(135);
        start.setStyle("-fx-background-color: #00C135; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(start);
        Button history = new Button("HISTORY");
        history.setPrefHeight(30);
        history.setPrefWidth(150);
        history.setLayoutX(230);
        history.setLayoutY(180);
        history.setStyle("-fx-background-color: #1c4aa1; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(history);
        Button shop = new Button("SHOP");
        shop.setPrefHeight(30);
        shop.setPrefWidth(150);
        shop.setLayoutX(230);
        shop.setLayoutY(225);
        shop.setStyle("-fx-background-color: rgba(208,165,16,0.71); -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(shop);

        Button profile = new Button("PROFILE");
        profile.setPrefHeight(30);
        profile.setPrefWidth(150);
        profile.setLayoutX(230);
        profile.setLayoutY(270);
        profile.setStyle("-fx-background-color: #9f08cb; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(profile);
        Button setting = new Button("SETTINGS");
        setting.setPrefHeight(30);
        setting.setPrefWidth(150);
        setting.setLayoutX(230);
        setting.setLayoutY(315);
        setting.setStyle("-fx-background-color: #0d795d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(setting);
        Button logout = new Button("LOG OUT");
        logout.setPrefHeight(30);
        logout.setPrefWidth(150);
        logout.setLayoutX(230);
        logout.setLayoutY(360);
        logout.setStyle("-fx-background-color: #cb1d1d; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(logout);
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Main.logged = false;
                    Login m = new Login();
                    m.start(Menu.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        history.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    History m = new History();
                    m.start(Menu.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Profile m = new Profile();
                    m.start(Menu.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        shop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Shop m = new Shop();
                    m.start(Menu.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        setting.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Settings m = new Settings();
                    m.start(Menu.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Image image = new Image("player.png");
        ImageView img = new ImageView(image);
        img.setLayoutX(10);
        img.setLayoutY(150);
        img.setFitWidth(80);
        img.setFitHeight(80);
        anchorPane.getChildren().add(img);
        Label det = new Label();
        det.setFont(new Font("Arial", 20));
        det.setTextFill(Color.WHITE);
        det.setLayoutX(10);
        det.setLayoutY(240);
        det.setText("COIN : " + a.coins + "\nHP : " + a.hp + "\nLEVEL : "+ a.level + "\nXP : " + a.xp);
        anchorPane.getChildren().add(det);
        Button car = new Button("CARDS");
        car.setPrefHeight(30);
        car.setPrefWidth(150);
        car.setLayoutX(20);
        car.setLayoutY(360);
        car.setStyle("-fx-background-color: #171ea9; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(car);
        car.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Dcard m = new Dcard();
                    m.start(Menu.stage);
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
    public static void change(boolean x) throws Exception {
        if(x){
            Menu.anchorPane = FXMLLoader.load(url2);
        }
        else{
            Menu.anchorPane = FXMLLoader.load(url);
        }
    }
}
