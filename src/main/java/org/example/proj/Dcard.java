package org.example.proj;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Card;
import logic.Main;
import logic.User;

import java.net.URL;

import static logic.Main.users;
import static org.example.proj.Start.scards;

public class Dcard extends Application {
    public static Stage stage;
    public static int adad;
    @Override
    public void start(Stage stage) throws Exception {
        Dcard.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("carddetail.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
        anchorPane.getChildren().add(ing);
        HBox hbox = new HBox();
        hbox.setLayoutX(100);
        hbox.setLayoutY(130);
        for (int i=0;i<a.cards.size();i++) {
            Card item = a.cards.get(i);
            Label itemLabel = new Label(item.getName());
            itemLabel.setTextFill(Color.WHITE);
            itemLabel.setStyle("-fx-padding: 10 ; -fx-border-color: white;");
            int finalI = i;
            itemLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Usercardsdetail.dlabel.setText(item.getdetails());
                adad = finalI;
                Usercardsdetail m = new Usercardsdetail();
                try {
                    m.start(Dcard.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            hbox.getChildren().add(itemLabel);
        }
        anchorPane.getChildren().add(hbox);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hbox);
        scrollPane.setLayoutX(100);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefViewportWidth(400);
        scrollPane.setPrefViewportHeight(35);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: BLack ; -fx-background-color: Black;");
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        anchorPane.getChildren().add(scrollPane);
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
                    m.start(Dcard.stage);
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
