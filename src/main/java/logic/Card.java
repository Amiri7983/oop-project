package logic;
import javafx.scene.image.ImageView;

import java.util.*;
public class Card {
    int coin;
    String type;
    //ArrayList<String> type = new ArrayList<>(Arrays.asList("normal","shield","heal","extrahp","change place" ,"repair","less round","delete card","card weaker" , "copier"));
    int ad;
    String name;
    int godrat = 0;
    int level = 1;
    int duration;
    int damage;
    int minlevel ;
    int point;
    public ImageView img;
    public String getName() {
        return this.name;
    }
    Card(String type, int coin){
        this.coin = coin;
        this.type = type;
    }
    Card(String name,int duration, int ad , int damage ,int point , int minlevevl) {
        this.name = name;
        this.type = "normal";
        this.coin = 15;
        this.damage= damage;
        this.ad = ad;
        this.duration = duration;
        this.point = point;
        this.minlevel= minlevel;
    }
    public Card(String name, String type, int duration , int damage, int ad, int coin, ImageView img){
        this.name = name;
        this.type = type;
        this.ad = ad;
        this.damage = damage;
        this.coin = coin;
        this.duration = duration;
        this.img = img;
    }
    public Card(String name, String type, int duration , int damage, int ad, int coin, ImageView img , int godrat){
        this.name = name;
        this.type = type;
        this.ad = ad;
        this.damage = damage;
        this.coin = coin;
        this.duration = duration;
        this.img = img;
        this.godrat = godrat;
    }
    public String getType() {
        return this.type;
    }
    public static void detail(ArrayList<Card> a){
        for(int i=0;i<a.size();i++){
            System.out.println(i);
            System.out.println(a.get(i).name + "\n"+a.get(i).type + "\n" + a.get(i).coin);
        }
    }
    public void getDetail(){
        System.out.println(this.name + "\n"+this.type + "\n" + this.coin+"\n" + this.damage + "\n" + this.ad + "\n" +
                this.duration + "\n" + this.minlevel + "\n" + this.point);
    }
    public String getcard(){
        return ("Name: " +this.name + "\nType: "+this.type + "\nCoin: " + this.coin+"\nDamage: " + this.damage + "\nAttak/Deffense: " + this.ad +
                "\nDuration: " + this.duration + "\nSpecial: "+this.godrat);
    }
    public String getdetails(){
        return ("Name: " +this.name + "\nType: "+this.type +"\nDamage: " + this.damage + "\nAttak/Deffense: " + this.ad +
                "\nDuration: " + this.duration );
    }
    public int getCoin() {
        return this.coin;
    }
    public void copy(Card a){
        a.name = this.name;
        a.type = this.type;
        a.coin = this.coin;
        a.damage= this.damage;
        a.ad = this.ad;
        a.duration = this.duration;
        a.point = this.point;
        a.minlevel= this.minlevel;
    }
    public void buy(User a){
        if (a.coins > this.coin){
            a.coins-=this.coin;
            System.out.println("card " + this.name + " successfully added!");
            a.cards.add(this);
        }
    }
    public int getAd() {
        return this.ad;
    }
    public void extrapower (Card a){
        a.damage += this.godrat;
    }
    public void heal(User a ){
        a.hp += this.godrat;
    }
    public void roundless(Game a){
        a.round--;
        // اینجا باید متناسب با اینکه مود  بازی چحوری زده شده باید تغییر کنه
    }
    public void shield(Card a){
        a.damage = 0;
    }
    public void copycard(User a , int index,Card b){
        a.cards.get(index).copy(b);
    }
    public void delete(User a , int index){
        a.cards.remove(index);
    }
    public void lesspower(User a ,int index1,int index2){
        a.cards.get(index1).damage-=5;
        a.cards.get(index2).ad-=5;
    }
    public void repair(Game a){
        //با توجه به اینکه مود بازی چجوری زده شده تعریفش میکنیم
    }
    public void change(Game a){
        //با توجه به اینکه مود بازی چجوری زده شده تعریفش میکنیم
    }
    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getACC() {
        return this.godrat;
    }

    public void setACC(int ad) {
        this.ad = ad;
    }
}
