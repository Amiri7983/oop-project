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
    public static int level =1;
    public int hp =100;
    public static int xp;
    public int coins = 100;
    public ArrayList<Card> cards;
    double damage = 100;
    String character;

    GameBoard gameBoard = new GameBoard();
    ArrayList<Card> hand;
    private static final int[] requiredXP = {100, 200, 300, 400, 500};
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
    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getXP() {
        return this.xp;
    }

    public int setXP(int xp) {
        return this.xp = xp;
    }

    public double getCoin() {
        return coins;
    }

    public void setCoin(double coins) {
        this.coins = (int)coins;
    }

    public double getHP() {
        return this.hp;
    }

    public void setHP(double hp) {
        this.hp = (int) hp;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return this.character;
    }

    public ArrayList<Card> getMyCard() {
        return Main.cards;
    }


    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String getCharacterString() {
        return character;
    }

    public int getLevel() {
        return this.level;
    }

    public void createHand() {
        Collections.shuffle(Main.cards);
        for (int i = 0; i < 5; i++) {
            hand.add(Main.cards.get(i));
        }
    }


    public void addAnotherCard(int playedRounds) {
        hand.add(Main.cards.get(5 + playedRounds));
    } // ye taghiri ijad kon ba komk cards4&6

    public void cards_4and6(User user2, Card card) {
        this.getHand().add(card);
        user2.getHand().remove(card); // shayad ham bejaye remove bayad null krd
    }

    public void cards_6(Card c) {
        this.getHand().add(c);
    }

    public void levelUp() {

    }

    void levelUp(int xp) {
        this.xp = xp;
        checkLevelUp();
    }

    private static void checkLevelUp() {
        while (xp >= requiredXP[level - 1]) {
            level++;
            xp = 0;
            congratulatePlayer();
        }
    }

    private static void congratulatePlayer() {
        System.out.println("Congratulations! You've Reached Level " + level + "!");
    }

    public static void showHistoryByDateAndTime(User user) {
        System.out.println(user.username + "'s History By Date And Time:");
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < StartGame.player1Username.size(); i++) {
            if (Objects.equals(StartGame.player1Username.get(i).username, user.username)) {
                temp.add(i);
            }
        }
        for(int i = 0; i < temp.size(); i++){
            System.out.println( (i + 1) +". Date And Time: " + StartGame.GameFinishedTime.get(temp.get(i)) + " Opponent (Level): " + StartGame.player2Username.get(temp.get(i)).username +" (" + StartGame.player2Level.get(temp.get(i)) + ")" + " Game State(Win/Loss):" + StartGame.player1GameState.get(temp.get(i)) + " Earnings: " + StartGame.player1Earnings);
        }

    }
    public static User getLoginUser() {
        User user = null;
        for (int i = 0; i < users.size(); i++) {
            if (Main.logged) {
                user = users.get(i);
            }
        }
        return user;
    }

    public static User getUserByName(String input) {
        int temp = 0;
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i) == input) ;
            temp = i;
        }
        return users.get(temp);
    }
    public static void showHistoryByOpponentName(User user){
        System.out.println(user.username + "'s History By Opponent's Name(Alphabetic):");
        ArrayList<Integer> temp1 = new ArrayList<>();
        ArrayList<String> usernameTemp = new ArrayList<>();
        for (int i = 0; i < StartGame.player1Username.size(); i++) {
            if (Objects.equals(StartGame.player1Username.get(i).username, user.username)) {
                temp1.add(i);
            }
        }

        for(int i = 0; i < StartGame.player2Username.size(); i++){
            usernameTemp.add(StartGame.player2Username.get(temp1.get(i)).username);
        }
        Collections.sort(usernameTemp);
        int count1 = usernameTemp.size();
        for(int i = 0; i < count1; i++){
            for(int j = 0; j < count1; j++){
                if (Objects.equals(StartGame.player2Username.get(j).username,usernameTemp.get(i))){
                    System.out.println("Date And Time: " + StartGame.GameFinishedTime.get(j) + " Opponent (Level): " + StartGame.player2Username.get(j).username +" (" + StartGame.player2Level.get(j) + ")" + " Game State(Win/Loss):" + StartGame.player1GameState.get(j) + " Earnings: " + StartGame.player1Earnings.get(j));
                }
            }
        }
    }
    public static void showHistoryByOpponentLevel(User user){
        System.out.println(user.username + "'s History Based On Opponent's Level");
        ArrayList<Integer> temp2 = new ArrayList<>();
        ArrayList<String> usernameTemp = new ArrayList<>();
        for (int i = 0; i < StartGame.player1Username.size(); i++) {
            if (Objects.equals(StartGame.player1Username.get(i).username, user.username)) {
                temp2.add(i);
            }
        }

        for(int i = 0; i < StartGame.player2Username.size(); i++){
            usernameTemp.add(StartGame.player2Username.get(temp2.get(i)).username);
        }
        Collections.sort(usernameTemp);
        int count1 = usernameTemp.size();
        for(int i = 0; i < count1; i++){
            for(int j = 0; j < count1; j++){
                if (Objects.equals(StartGame.player2Username.get(j).username,usernameTemp.get(i))){
                    System.out.println("Date And Time: " + StartGame.GameFinishedTime.get(j) + " Opponent (Level): " + StartGame.player2Username.get(j).username +" (" + StartGame.player2Level.get(j) + ")" + " Game State(Win/Loss):" + StartGame.player1GameState.get(j) + " Earnings: " + StartGame.player1Earnings.get(j));
                }
            }
        }
    }
}