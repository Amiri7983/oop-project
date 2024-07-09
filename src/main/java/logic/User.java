package logic;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.util.*;

import static javafx.scene.paint.Color.WHITE;
import static logic.Control.errorlbl;
import static logic.Main.*;

public class User {
    public static boolean run = false;
    public static boolean em =false;
    //public static boolean  logged =false;
    public String username;
    @FXML
    static TextField unf,psf;
    //@FXML
    //static Label errorlbl;
    public String password;
    public String nickname;
    public String email;
    public String answer1= null;
    String answer2 = null;
    String answer3 = null;
    String confirm;
    public int level =1;
    public int hp =100;
    public int xp;
    public int coins = 100;
    public ArrayList<Card> cards;
    int number;
    public User(String username, String password, String email, String nickname, String answer1){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.answer1 = answer1;
//        this.answer2 = answer2;
//        this.answer3 = answer3;
        this.username = username;
        this.cards = new ArrayList<>();
    }
    public static void login(String username, String password) throws Exception {
        int n = -1;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).username)) {
                n = i;
                Main.index = i;
            }
        }
        if (n == -1) {
            System.out.println("Username not found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username not found");
            alert.show();
        }
        else if (username.equals(users.get(n).username) && !(password.equals(users.get(n).password))) {
            long start = System.currentTimeMillis();
            System.out.println("the password is not correct");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("the password is not correct");
            alert1.showAndWait();
            step++;
            try {
                Thread.sleep(step * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            end = System.currentTimeMillis();
            System.out.println("try again in " + 1000 * (end - start) + " seconds");
        }
        else if ((username.equals(users.get(n).username)) && (password.equals(users.get(n).password))) {
            System.out.println("user logged in successfully!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("user logged in successfully!");
            alert.showAndWait();
            Main.logged = true;
        }
    }
    public static void adduser(String username, String password, String confirm, String email, String nickname){
        if(!(email.matches("\\s*+(\\w)+@+(\\w)+.com+\\s*")) && !(email.equals("random"))){
            System.out.println("invalid email");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("invalid email");
            alert.showAndWait();
        }
        else if(!(username.matches("(\\w+)"))){
            System.out.println("invaild username!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("invaild username!");
            alert.showAndWait();
        }
        else if(usernames.contains(username)){
            System.out.println("repeated username!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("repeated username!");
            alert.showAndWait();
        }
        else if(password.length() < 8){
            System.out.println("weak pass;\nit must has at least 8 characters!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("weak pass;\nit must has at least 8 characters!");
            alert.showAndWait();
        }
        else if(!(password.matches("(\\w+)"))){
            System.out.println("password must contain a charcter like *-!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password must contain a charcter like *-!");
            alert.showAndWait();
        }
        else if(!(password.equals(confirm))){
            System.out.println("please chack the confirmation!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please chack the confirmation!");
            alert.showAndWait();
        }
        else{
            usernames.add(username);
            System.out.println("sign up complete!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("sign up complete!");
            alert.showAndWait();
            run = true;
        }
    }
    public void detail(){
        System.out.println(this.username);
        System.out.println(this.level);
        System.out.println(this.coins);
    }
    public static void changemail (String email){
        if (!(email.matches("\\s*+(\\w)+@+(\\w)+.com *")) && !(email.equals("random"))) {
            System.out.println("invalid email");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("invalid email!");
            alert.showAndWait();
        }
        else {
            System.out.println("email changed successfully");
            users.get(Main.index).email = email;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("email changed successfully!");
            alert.showAndWait();
            em = true;
        }
    }
    public static void detail2 (String a){
        System.out.println("username: " + users.get(index).username);
        System.out.println("password: " + users.get(index).password);
        System.out.println("email: " + users.get(index).email);
    }
    public static void changepass (String password){
        if (password.length() < 8) {
            System.out.println("weak pass;\nit must has at least 8 characters!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("weak pass;\nit must has at least 8 characters!");
            alert.showAndWait();
        }
        else if (!(password.matches("(\\w+)"))) {
            System.out.println("password must contain a charcter like *-!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password must contain a charcter like *-!!");
            alert.showAndWait();
        }
        else {
            users.get(index).password = password;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("password changed successfully\nyour password: " + password);

            alert.showAndWait();
        }
    }
    public static String randpass () {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
    public static boolean userval (String u){
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(u)) {
                return false;
            }
        }
        return true;
    }
    public static boolean valid (Integer a, User b, String c){
        if (a == 1) {
            if (c.equals(b.answer1)) {
                return true;
            }
        }
        else if (a == 2) {
            if (c.equals(b.answer2)) {
                return true;
            }
        }
        else if (a == 3) {
            if (c.equals(b.answer3)) {
                return true;
            }
        }
        return false;
    }
}
