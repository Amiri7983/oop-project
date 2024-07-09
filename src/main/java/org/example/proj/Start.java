package org.example.proj;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Card;
import logic.Main;
import logic.User;

import java.net.URL;
import java.util.ArrayList;


public class Start extends Application{
    public static Stage stage;
    public static ArrayList<Card> scards = new ArrayList<>();
    public static ArrayList<Card> fcards = new ArrayList<>();
    @Override
    public void start(Stage stage) throws Exception{
        Image in1 = new Image("card1.png");
        ImageView img1 = new ImageView(in1);
        Image in2 = new Image("card2.png");
        ImageView img2 = new ImageView(in2);
        Image in3 = new Image("card3.png");
        ImageView img3 = new ImageView(in3);
        Image in4 = new Image("card4.png");
        ImageView img4 = new ImageView(in4);
        Image in5 = new Image("card5.png");
        ImageView img5 = new ImageView(in5);
        Image in6 = new Image("card6.png");
        ImageView img6 = new ImageView(in6);
        Image in7 = new Image("shield.png");
        ImageView img7 = new ImageView(in7);
        Image in8 = new Image("extrap.png");
        ImageView img8 = new ImageView(in8);
        Image in9 = new Image("heal.png");
        ImageView img9 = new ImageView(in9);
        Image in10 = new Image("lessp.png");
        ImageView img10 = new ImageView(in10);
        Image in11 = new Image("lessr.png");
        ImageView img11 = new ImageView(in11);
        Image in12 = new Image("changer.png");
        ImageView img12 = new ImageView(in12);
        Image in13 = new Image("repair.png");
        ImageView img13 = new ImageView(in13);
        Image in14 = new Image("Card14.png");
        ImageView img14 = new ImageView(in14);
        Image in15 = new Image("Card15.png");
        ImageView img15 = new ImageView(in15);
        Image in16 = new Image("Card16.png");
        ImageView img16 = new ImageView(in16);
        Image in17 = new Image("Card17.png");
        ImageView img17 = new ImageView(in17);
        Image in18 = new Image("Card18.png");
        ImageView img18 = new ImageView(in18);
        Image in19 = new Image("Card19.png");
        ImageView img19 = new ImageView(in19);
        Image in20 = new Image("Card20.png");
        ImageView img20 = new ImageView(in20);

        scards.add(new Card("Card1","normal",4,34,40,25,img1));
        scards.add(new Card("Card2","normal",5,35,45,25,img2));
        scards.add(new Card("Card3","normal",4,39,48,30,img3));
        scards.add(new Card("Card4","normal",1,37,48,45,img4));
        scards.add(new Card("Card5","normal",3,38,45,30,img5));
        scards.add(new Card("Card6","normal",5,35,55,25,img6));
        scards.add(new Card("shield","shield",2,0,0,20,img7));
        scards.add(new Card("extra power","extrapower",1,0,0,20,img8 , 15));
        scards.add(new Card("heal","heal",1,0,0,20,
                img9,10));
        scards.add(new Card("less power","lesspower",1,0,0,20,img10 , 10));
        scards.add(new Card("less round","lessround",1,0,0,20,img11 , 10));
        scards.add(new Card("change","change",1,0,0,20,img12 , 0));
        scards.add(new Card("repair","repair",1,0,0,20,img13 , 10));
        scards.add(new Card("Card14","normal",2,28,30,25,img14));
        scards.add(new Card("Card15","normal",3,27,30,25,img15));
        scards.add(new Card("Card16","normal",2,30,26,25,img16));
        scards.add(new Card("Card17","normal",3,36,30,25,img17));
        scards.add(new Card("Card18","normal",5,30,30,25,img18));
        scards.add(new Card("Card19","normal",3,27,30,25,img19));
        scards.add(new Card("Card20","normal",1,31,15,25,img20));
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fcards.add(new Card("Fcard1","normal",4,34,40,25,img1));
        Start.stage = stage;
        User amiri = new User("amirreza","amiri1683","amir@yahoo.com","amiri","aliakbar");
        Main.users.add(amiri);
        URL url = Start.class.getResource("start.fxml");
        AnchorPane anchorPane = FXMLLoader.load(url);
        Image ima = new Image("label.png");
        ImageView ing = new ImageView(ima);
        ing.setFitWidth(300);
        ing.setFitHeight(70);
        ing.setLayoutX(150);
        ing.setLayoutY(40);
        anchorPane.getChildren().add(ing);
        Button su= new Button("SIGN UP");
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
                    Signup b = new Signup();
                    b.start(Start.stage);
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
