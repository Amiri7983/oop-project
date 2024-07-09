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
        Image in14 = new Image("card14.png");
        ImageView img14 = new ImageView(in14);
        Image in15 = new Image("card15.png");
        ImageView img15 = new ImageView(in15);
        Image in16 = new Image("card16.png");
        ImageView img16 = new ImageView(in16);
        Image in17 = new Image("card17.png");
        ImageView img17 = new ImageView(in17);
        Image in18 = new Image("card18.png");
        ImageView img18 = new ImageView(in18);
        Image in19 = new Image("card19.png");
        ImageView img19 = new ImageView(in19);
        Image in20 = new Image("card20.png");
        ImageView img20 = new ImageView(in20);

        scards.add(new Card("Card1","normal",4,34,40,25,img1));
        scards.add(new Card("Card2","normal",5,35,45,25,img2));
        scards.add(new Card("Card3","normal",4,39,48,30,img3));
        scards.add(new Card("Card4","normal",1,37,48,45,img4));
        scards.add(new Card("Card5","normal",3,38,45,30,img5));
        scards.add(new Card("Card6","normal",5,35,55,25,img6));
        scards.add(new Card("shield","shield",2,0,0,20,img7));
        scards.add(new Card("extra power","extrapower",1,0,0,20,img8 , 15));
        scards.add(new Card("heal","heal",1,0,0,20, img9,10));
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
        Image inage1 = new Image("fcard1.png");
        ImageView image1 = new ImageView(inage1);
        Image inage2 = new Image("fcard2.png");
        ImageView image2 = new ImageView(inage2);
        Image inage3 = new Image("fcard3.png");
        ImageView image3 = new ImageView(inage3);
        Image inage4 = new Image("fcard4.png");
        ImageView image4 = new ImageView(inage4);
        Image inage5 = new Image("fcard5.png");
        ImageView image5= new ImageView(inage5);
        Image inage6 = new Image("fcard6.png");
        ImageView image6= new ImageView(inage6);
        Image inage7 = new Image("fcard7.png");
        ImageView image7= new ImageView(inage7);
        Image inage8 = new Image("fcard8.png");
        ImageView image8= new ImageView(inage8);
        Image inage9 = new Image("fcard9.png");
        ImageView image9= new ImageView(inage9);
        Image inage10 = new Image("fcard10.png");
        ImageView image10 = new ImageView(inage10);
        Image inage11 = new Image("fcard11.png");
        ImageView image11= new ImageView(inage11);
        Image inage12 = new Image("fcard12.png");
        ImageView image12= new ImageView(inage12);
        Image inage13 = new Image("fcard13.png");
        ImageView image13= new ImageView(inage13);
        Image inage14 = new Image("fcard14.png");
        ImageView image14 = new ImageView(inage14);
        Image inage15 = new Image("fcard15.png");
        ImageView image15= new ImageView(inage15);
        Image inage16 = new Image("fcard16.png");
        ImageView image16= new ImageView(inage16);
        Image inage17 = new Image("fcard17.png");
        ImageView image17= new ImageView(inage17);
        Image inage18 = new Image("fcard18.png");
        ImageView image18= new ImageView(inage18);
        Image inage19 = new Image("fcard19.png");
        ImageView image19= new ImageView(inage19);
        Image inage20 = new Image("fcard20.png");
        ImageView image20= new ImageView(inage20);
        fcards.add(new Card("Fcard1","normal",5,30,25,25,image1));
        fcards.add(new Card("Fcard2","normal",4,28,36,25,image2));
        fcards.add(new Card("Fcard3","normal",1,31,15,25,image3));
        fcards.add(new Card("Fcard4","normal",5,27,35,25,image4));
        fcards.add(new Card("Fcard5","normal",2,32,36,25,image5));
        fcards.add(new Card("Fcard6","normal",2,33,36,25,image6));
        fcards.add(new Card("Fcard7","normal",3,27,15,25,image7));
        fcards.add(new Card("Fcard8","normal",1,27,28,25,image8));
        fcards.add(new Card("Fcard9","normal",3,34,48,25,image9));
        fcards.add(new Card("Fcard10","normal",5,25,45,25,image10));
        fcards.add(new Card("Fcard11","normal",1,28,15,25,image11));
        fcards.add(new Card("Fcard12","normal",3,32,33,25,image12));
        fcards.add(new Card("Fcard13","normal",1,25,45,25,image13));
        fcards.add(new Card("Fcard14","normal",5,35,45,25,image14));
        fcards.add(new Card("Fcard15","normal",2,29,20,25,image15));
        fcards.add(new Card("Fcard16","normal",1,37,25,25,image16));
        fcards.add(new Card("Fcard17","normal",2,32,34,25,image17));
        fcards.add(new Card("Fcard18","normal",3,30,39,25,image18));
        fcards.add(new Card("Fcard19","normal",5,29,25,25,image19));
        fcards.add(new Card("Fcard20","normal",3,28,15,25,image20));
        /////////////////////////////////////////////////////////////////////
        Start.stage = stage;
        User amiri = new User("amirreza","amiri1683","amir@yahoo.com","amiri","aliakbar");
        Main.users.add(amiri);
        amiri.cards.addAll(fcards);
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
