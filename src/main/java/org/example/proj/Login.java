package org.example.proj;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logic.Main;
import logic.User;

import java.net.URL;

//import static logic.Control.psf;
//import static logic.Control.unf;

public class Login extends Application {
    public static Stage stage;
    static String uv,pv;
    @FXML
   public TextField unf,psf;
//    @FXML
//    public void initialize() {
//        unf.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                uv = newValue;
//            }
//        });
//    }
////    @FXML
////    public void initialize1() {
////        psf.textProperty().addListener(new ChangeListener<String>() {
////            @Override
////            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
////                pv = newValue;
////            }
////        });
////    }
//@FXML
//private void initialize1() {
//    // Add listener to handle changes in usernameField
//    psf.textProperty().addListener((observable, oldValue, newValue) -> {
//        //System.out.println("Username changed to: " + newValue);
//        // Perform any validation or other actions here
//        pv = newValue;
//    });
//}
@FXML
public void initialize() {
    unf.textProperty().addListener((observable, oldValue, newValue) -> {
        uv = newValue;
    });
    psf.textProperty().addListener((observable, oldValue, newValue) -> {
        pv = newValue;
    });
}
    @Override
    public void start(Stage stage) throws Exception{
        Login.stage = stage;
        URL url = Start.class.getResource("login.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(40);
        anchorPane.getChildren().add(ing);
        Button log = new Button("LOGIN");
        log.setPrefHeight(26);
        log.setPrefWidth(74);
        log.setLayoutX(258);
        log.setLayoutY(340);
        log.setStyle("-fx-background-color: #00C135; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(log);
        log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    User.login(uv,pv);
                    //Menu m= new Menu();
                    //m.start(Login.stage);
                    if(Main.logged == true){
                        Menu m= new Menu();
                        m.start(Login.stage);
                    }
                    else{
                        restart();
                    }
                    //
                    System.out.println(pv);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button forg = new Button("FORGET");
        forg.setPrefHeight(26);
        forg.setPrefWidth(74);
        forg.setLayoutX(258);
        forg.setLayoutY(375);
        forg.setStyle("-fx-background-color: #b90f23; -fx-text-fill: white ; -fx-background-radius:30 ;");
        anchorPane.getChildren().add(forg);
        forg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Forget  f = new Forget();
                    f.start(Login.stage);
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
    public static void restart() throws Exception {
        Login a = new Login();
        a.start(Login.stage);
    }

}
