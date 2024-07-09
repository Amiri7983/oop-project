package org.example.proj;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class Signup  extends Application {
    static String unv,psv,pscv,nnv,ev,sqv,nuv,cav;
    @FXML
    public TextField username,pass,passc,nickname,email,sqt,capchat,nt;
    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            unv = newValue;
        });
        pass.textProperty().addListener((observable, oldValue, newValue) -> {
            psv = newValue;
        });
        passc.textProperty().addListener((observable, oldValue, newValue) -> {
            pscv = newValue;
        });
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {
            nnv = newValue;
        });
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            ev = newValue;
        });
        sqt.textProperty().addListener((observable, oldValue, newValue) -> {
            sqv = newValue;
        });
        capchat.textProperty().addListener((observable, oldValue, newValue) -> {
            cav = newValue;
        });
        nt.textProperty().addListener((observable, oldValue, newValue) -> {
            nuv = newValue;
        });
    }
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Signup.stage = stage;
        URL url = Start.class.getResource("Signup.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(40);
        anchorPane.getChildren().add(ing);
        Button rand = new Button("RANDOM");
        rand.setPrefHeight(26);
        rand.setPrefWidth(70);
        rand.setLayoutX(488);
        rand.setLayoutY(365);
        rand.setStyle("-fx-background-color: #a29b04; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(rand);
        rand.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String s = User.randpass();
                    psv = pscv = s;
                    System.out.println(s);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("your random password: " + s);
                    alert.showAndWait();

                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button sub = new Button("SIGN UP");
        sub.setPrefHeight(26);
        sub.setPrefWidth(170);
        sub.setLayoutX(308);
        sub.setLayoutY(365);
        sub.setStyle("-fx-background-color: #00C135; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(sub);
        sub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    User.adduser(unv,psv,pscv,ev,nnv);
                    if(User.run == true ){
                        if(cav.equals("4")) {
                            User a = new User(unv, psv, ev, nnv, sqv);
                            users.add(a);
                            Login m = new Login();
                            m.start(Signup.stage);
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("wrong capcha!");
                            alert.showAndWait();
                        }
                    }
                    else{
                        restart();
                    }
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
    public void restart() throws Exception {
        Signup a = new Signup();
        a.start(Signup.stage);
    }
}
