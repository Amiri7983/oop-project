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
import logic.Main;
import logic.StartGame;
import logic.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static logic.Main.users;

public class History1 extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        History1.stage = stage;
        User a = users.get(Main.index);
        URL url = Start.class.getResource("history1.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(100);
        ing.setLayoutX(150);
        ing.setLayoutY(30);
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
                    History m = new History();
                    m.start(History1.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ArrayList<Integer> temp1 = new ArrayList<>();
        ArrayList<String> usernameTemp = new ArrayList<>();
        for (int i = 0; i < StartGame.player1Username.size(); i++) {
            if (Objects.equals(StartGame.player1Username.get(i).username, a.username)) {
                temp1.add(i);
            }
        }

        for(int i = 0; i < StartGame.player2Username.size(); i++){
            usernameTemp.add(StartGame.player2Username.get(temp1.get(i)).username);
        }
        Collections.sort(usernameTemp);
        int count1 = usernameTemp.size();
        VBox vbox = new VBox();
        vbox.setLayoutX(100);
        vbox.setLayoutY(150);
        for(int i = 0; i < count1; i++){
            HBox hbox = new HBox();
            hbox.setLayoutX(100);
            hbox.setLayoutY(150+i*40);
            for(int j = 0; j < count1; j++){
                if (Objects.equals(StartGame.player2Username.get(j).username,usernameTemp.get(i))){
                    Label itemLabel1 = new Label("DATE AND TIME: "+StartGame.GameFinishedTime.get(j));
                    itemLabel1.setTextFill(Color.WHITE);
                    itemLabel1.setStyle("-fx-padding: 10 ; -fx-border-color: white;");
                    Label itemLabel2 = new Label("LEVEL(OPPONENT): "+ StartGame.player2Username.get(j).username+" (" + StartGame.player2Level.get(j) + ")" );
                    itemLabel2.setTextFill(Color.WHITE);
                    itemLabel2.setStyle("-fx-padding: 10 ; -fx-border-color: white;");
                    Label itemLabel3 = new Label("GAME STATE: "+StartGame.player1GameState.get(j));
                    itemLabel3.setTextFill(Color.WHITE);
                    itemLabel3.setStyle("-fx-padding: 10 ; -fx-border-color: white;");
                    Label itemLabel4 = new Label("EARNINGS: "+ StartGame.player1Earnings.get(j));
                    itemLabel4.setTextFill(Color.WHITE);
                    itemLabel4.setStyle("-fx-padding: 10 ; -fx-border-color: white;");
                    hbox.getChildren().addAll(itemLabel1,itemLabel2,itemLabel3,itemLabel4);
                    System.out.println("Date And Time: " + StartGame.GameFinishedTime.get(j) + " Opponent (Level): " + StartGame.player2Username.get(j).username +" (" + StartGame.player2Level.get(j) + ")" + " Game State(Win/Loss):" + StartGame.player1GameState.get(j) + " Earnings: " + StartGame.player1Earnings.get(j));
                }
            }
            vbox.getChildren().add(hbox);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        scrollPane.setLayoutX(100);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefViewportWidth(400);
        scrollPane.setPrefViewportHeight(35);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show horizontal scrollbar as needed
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Never show vertical scrollbar
        scrollPane.setStyle("-fx-background: BLack ; -fx-background-color: Black;");
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        anchorPane.getChildren().add(scrollPane);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("CITY WARS : TOKYO");
        stage.show();
    }
}
