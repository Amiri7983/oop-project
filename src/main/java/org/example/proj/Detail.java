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
import logic.Card;
import logic.Main;
import logic.User;

import java.net.URL;

import static logic.Main.users;
import static org.example.proj.Start.scards;

public class Detail extends Application {
    public static Stage stage;
    public static Label dlabel = new Label();
    @Override
    public void start(Stage stage) throws Exception {
        Detail.stage = stage;
        User a = users.get(Main.index);
        Card b = scards.get(Shop.adad);
        URL url = Start.class.getResource("menu.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
        anchorPane.getChildren().add(ing);
        dlabel.setLayoutX(100);
        dlabel.setLayoutY(120);
        dlabel.setFont(new Font("Arial", 20));
        dlabel.setTextFill(Color.WHITE);
        anchorPane.getChildren().add(dlabel);
        Button ad = new Button("ADD");
        ad.setPrefHeight(26);
        ad.setPrefWidth(64);
        ad.setLayoutX(448);
        ad.setLayoutY(340);
        ad.setStyle("-fx-background-color: #0ea106; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(ad);
        ad.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    a.cards.add(scards.get(Shop.adad));
                    if(a.coins >=b.getCoin() ){
                        a.coins -= b.getCoin();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("card added successfully");
                        alert.showAndWait();
                        Shop m = new Shop();
                        m.start(Detail.stage);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("don't have enough coins!");
                        alert.showAndWait();
                        Detail m = new Detail();
                        m.start(Detail.stage);
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
                    Shop m = new Shop();
                    m.start(Detail.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ImageView z = b.img;
        z.setLayoutX(300);
        z.setLayoutY(140);
        z.setFitWidth(122);
        z.setFitHeight(200);
        anchorPane.getChildren().add(z);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("CITY WARS : TOKYO");
        stage.show();
    }
}
