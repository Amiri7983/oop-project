package logic;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.proj.Menu;
import org.example.proj.Start;

import java.util.ArrayList;
import java.util.*;

import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.WHITE;
import static logic.Card.detail;

public class Main {
    //static boolean run = false;
    //@FXML
    //static TextField unf, psf;
    //@FXML
    //static Button loginbtn;
    //@FXML
    //static Label errorlbl;
    //@FXML
    //static TextField username,pass,passc,nickname,email,sqt,nt,capchat;
    //@FXML
    //static Label cpchalbl;
    static int step = 0;
    public static boolean logged = false;
    public static ArrayList<String> names = new ArrayList<>();
    public static long start, end;
    public static int index;
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<String> usernames = new ArrayList<String>();
    public static ArrayList<Card> cards = new ArrayList<Card>();
    public static int[] adad = {1, 2, 4, 8, 16};
    public static int min = 1;
    public static int max = 10;
    public static Random rand = new Random();
    public static int x = (rand.nextInt((max - min) + 1) + min) % 4;
    public static int y = (rand.nextInt((max - min) + 1) + min) % 2;

    public static void main(String[] args) throws Exception {
        User amiri = new User("amirreza", "amiri1683", "amir@yahoo.com", "amiri", "aliakbar");
        users.add(amiri);
        //System.out.println("please select:\n1-sign up\n2-sign in");
        System.out.println("security question;\n1-What is your father’s name ? \n2-What is your favourite color ? \n3-What was the name of your first pet?");
        System.out.println("capcha: " + adad[y] + "x - " + adad[x] + " = 0 ");
        Scanner scan = new Scanner(System.in);
        boolean ab = true;
        //boolean admin = false;
        while (ab) {
            String[] in = new String[9];
            in = scan.nextLine().split(" ");
            //String [] co = new String[7];
            if (in[0].equals("end")) {
                ab = false;
            } else if (in[0].equals("user") && in[1].equals("create")) {
                if (in.length < 7 && !(in[3].equals("random"))) {
                    System.out.println("Please complete all parts!");
                } else if (Double.parseDouble(in[9]) != adad[x] / adad[y]) {
                    System.out.println("please enter the right capcha!");
                } else {
                    if (in[3].equals("random")) {
                        in[3] = in[4] = User.randpass();
                        System.out.println("the random password = " + in[3]);
                    }
                    User.adduser(in[2], in[3], in[4], in[5], in[6]);
                    //run = true;
                    //logged = true;
                    if (in[7].equals("1") && User.run == true) {
                        User a = new User(in[2], in[3], in[5], in[6], in[8]);
                        users.add(a);
                        System.out.println("done 1!");
                        User.run = false;
                    } else if (in[7].equals("2") && User.run == true) {
                        User a = new User(in[2], in[3], in[5], in[6], in[8]);
                        users.add(a);
                        System.out.println("done 2!");
                        User.run = false;
                    } else if (in[7].equals("3") && User.run == true) {
                        User a = new User(in[2], in[3], in[5], in[6], in[8]);
                        users.add(a);
                        System.out.println("done 3!");
                        User.run = false;
                    }
                }
            }
            else if (in[0].equals("user") && in[1].equals("login") && logged == false) {
                User.login(in[2], in[3]);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).username == in[2]) {
                        index = i;
                    }
                }
                //logged = true;
            } else if (in[0].equals("user") && in[1].equals("login") && logged == true) {
                System.out.println(" a user already logged in ");
            } else if (in[0].equals("forgot") && in[1].equals("my") && in[2].equals("password") && logged == true) {
                for (int i = 0; i < users.size(); i++) {
                    if (User.valid(Integer.parseInt(in[3]), users.get(i), in[4])) {
                        System.out.println("your password: " + users.get(i).password);
                    }
                }
            } else if (in[0].equals("log") && in[1].equals("out")) {
                logged = false;
            } else if (in[0].equals("show") && in[1].equals("information") && logged == true) {
                User.detail2(in[2]);
            } else if (in[0].equals("show") && in[1].equals("information") && logged == false) {
                System.out.println("please login first!");
            } else if (in[0].equals("profile") && in[1].equals("change") && in[2].equals("username") && logged == true) {
                if (User.userval(in[3])) {
//                    for(int i=0;i<users.size();i++){
//                        if(users.get(i).username.equals(in[2])){
//                            users.get(i).username = in[3];
//                            System.out.println("username changed successfully");
//                        }
//                    }
                    users.get(index).username = in[3];
                    System.out.println("username changed successfully");
                } else {
                    System.out.println("repeated username!!");
                }
            } else if (in[0].equals("profile") && in[1].equals("change") && in[2].equals("password")) {
                if (in[3].equals(in[4])) {
                    System.out.println("Please enter a new password! ");
                } else if (!(in[3].equals(users.get(index).password))) {
                    System.out.println("Current password is incorrect!");
                } else {
                    User.changepass(in[4]);
                }
            } else if (in[0].equals("profile") && in[1].equals("change") && in[2].equals("nickname") && logged == true) {
                users.get(index).nickname = in[3];
                System.out.println("nicknamme changed successfully");
            } else if (in[0].equals("profile") && in[1].equals("change") && in[2].equals("email") && logged == true) {
                User.changemail(in[3]);
            }
            else if (in[0].equals("menu") && in[1].equals("shop") && logged == true) {
                System.out.println("shop: ");
                Card.detail(cards);
                while (users.get(index).coins > 0) {
                    int n = scan.nextInt();
                    cards.get(n).buy(users.get(index));
                }
            }
            else if (in[0].equals("login") && in[1].equals("admin")) {
                if (in[2].equals("admin1402")) {
                    System.out.println("admin successfully login!");
                } else {
                    System.out.println("invalid password");
                }
            }
            else if (in[0].equals("admin") && in[1].equals("addcard")) {
                addcard(in[2], Integer.parseInt(in[3]), Integer.parseInt(in[4]), Integer.parseInt(in[5]),
                        Integer.parseInt(in[6]), Integer.parseInt(in[7]));
                names.add(in[2]);
            }
            else if (in[0].equals("admin") && in[1].equals("showcards")) {
                for (int i = 0; i < cards.size(); i++) {
                    System.out.println(i);
                    cards.get(i).getDetail();
                }
            }
            else if (in[0].equals("admin") && in[1].equals("edit")) {
                int s = Integer.parseInt(in[2]);
                //int r = Integer.parseInt(in[3]);
                System.out.println("are you sure you want to edit this card?");
                if (scan.nextLine().equals("y")) {
                    if (in[3].equals("name")) {
                        if (names.contains("name")) {
                            System.out.println("repeated name!");
                        } else {
                            cards.get(s).name = in[4];
                        }
                    }
                    else if (in[3].equals("point")) {
                        cards.get(s).point = Integer.parseInt(in[4]);
                        System.out.println("card successfully edited");
                    }
                    else if (in[3].equals("attack/defence")) {
                        cards.get(s).ad = Integer.parseInt(in[4]);
                        System.out.println("card successfully edited");
                    }
                    else if (in[3].equals("duration")) {
                        cards.get(s).duration = Integer.parseInt(in[4]);
                        System.out.println("card successfully edited");
                    }
                    else if (in[3].equals("damage")) {
                        cards.get(s).damage = Integer.parseInt(in[4]);
                        System.out.println("card successfully edited");
                    }
                    else if (in[3].equals("minlevel")) {
                        cards.get(s).minlevel = Integer.parseInt(in[4]);
                        System.out.println("card successfully edited");
                    }
                }
            }
            else if (in[0].equals("admin") && in[1].equals("delete")) {
                int s = Integer.parseInt(in[2]);
                System.out.println("are you sure you want to delete this card?");
                if (scan.nextLine().equals("y")) {
                    cards.remove(s);
                } else {
                    System.out.println("invalid input!");
                }
            }
            else if (in[0].equals("admin") && in[1].equals("showusers")) {
                for (int i = 0; i < users.size(); i++) {
                    users.get(i).detail();
                }
            }
            else if (in[0].equals("main") && in[1].equals("menu") && logged == true){
                System.out.println("start");
                System.out.println("show cards");
                System.out.println("history");
                System.out.println("shop");
                System.out.println("log out");
            }
            else if (in[0].equals("menu") && in[1].equals("show cards")) {
                users.get(index).cards.toString();
            }
            else if (in[0].equals("menu") && in[1].equals("history")) {
                //بعد از زدن مود بازی تعریف میکنیم
            }
            else if(in[0].equals("menu") && in[1].equals("log out")){
                logged = false;
            }
        }
        //loginbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
        //    @Override
        //    public void handle(MouseEvent mouseEvent) {
        //        try {
        //            User.login(unf.getText(),psf.getText());
        //            Menu m= new Menu();
        //            m.start(Start.stage);
        //        }
        //        catch (Exception e) {
        //            throw new RuntimeException(e);
        //        }
        //    }
        //});
        //launch(args);

    }

    public static void addcard (String name,int duration, int ad, int damage, int point, int minlevevl){
        Card a = new Card(name, duration, ad, damage, point, minlevevl);
        cards.add(a);
        System.out.println("card added successfully!");
    }
}
