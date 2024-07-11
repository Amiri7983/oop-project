package logic;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.proj.Login;

import java.net.URL;


public class Start extends Application{
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception{
        Start.stage = stage;
        URL url = Start.class.getResource("start.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(40);
        anchorPane.getChildren().add(ing);
        Button su= new Button("SIGN IN");
        su.setPrefHeight(50);
        su.setPrefWidth(200);
        su.setLayoutX(200);
        su.setLayoutY(200);
        su.setStyle("-fx-background-color: #003C7C; -fx-text-fill: white; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(su);
        Button lo = new Button("LOGIN");
        lo.setPrefHeight(50);
        lo.setPrefWidth(200);
        lo.setLayoutX(200);
        lo.setLayoutY(280);
        lo.setStyle("-fx-background-color: #003C7C; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(lo);
        lo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Login a = new Login();
                    a.start(Start.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        su.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Login a = new Login();
                    a.start(Start.stage);
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

