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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Card;
import logic.Main;
import logic.User;

import java.net.URL;

import static logic.Main.users;
import static org.example.proj.Start.scards;

public class Shop extends Application {
    public static Stage stage;
    public static int adad ;
    @Override
    public void start(Stage stage) throws Exception {
        Shop.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("shop.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(100);
        ing.setLayoutX(150);
        ing.setLayoutY(15);
        anchorPane.getChildren().add(ing);
        HBox hbox = new HBox();
        hbox.setLayoutX(100);
        hbox.setLayoutY(130);
        //hbox.setPrefWidth(100);
        //hbox.setPrefHeight(30);
        for (int i=0;i<scards.size();i++) {
            Card item = scards.get(i);
            Label itemLabel = new Label(item.getName());
            itemLabel.setTextFill(Color.WHITE);
            itemLabel.setStyle("-fx-padding: 10 ; -fx-border-color: white;");
//            itemLabel.setPrefHeight(20);
//            itemLabel.setPrefHeight(100);
//            itemLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
//            itemLabel.setWrapText(true);
            int finalI = i;
            itemLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                //itemLabel.setPrefWidth(100);
                Detail.dlabel.setText(item.getcard());
                adad = finalI;
                Detail m = new Detail();
                try {
                    m.start(Shop.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            hbox.getChildren().add(itemLabel);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hbox);
        scrollPane.setLayoutX(100);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefViewportWidth(400);
        scrollPane.setPrefViewportHeight(35);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show horizontal scrollbar as needed
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Never show vertical scrollbar
        scrollPane.setStyle("-fx-background: BLack ; -fx-background-color: Black;");
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        anchorPane.getChildren().add(scrollPane);
        // Create VBox and add ScrollPane and detail label to it
//        VBox root = new VBox(20);
//        root.getChildren().addAll(scrollPane, Detail.dlabel);
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
                    m.start(Shop.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        anchorPane.getChildren().add(hbox);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("CITY WARS : TOKYO");
        stage.show();
    }
}
