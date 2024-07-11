package logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StartGame {
    private static User user1;
    private static User user2;
    int bet;
    static boolean player1turn = false;
    private static final int BLOCK_COUNT = 21;
    private boolean[] blocks = new boolean[BLOCK_COUNT];
    private Random random = new Random();
    static LocalDateTime currentDateTime = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static ArrayList<User> player1Username;
    public static ArrayList<User> player2Username;
    public static ArrayList<String> player1GameState;
    public static ArrayList<String> player2GameState;
    public static ArrayList<String> GameFinishedTime;
    public static ArrayList<String> player1Earnings;
    public static ArrayList<String> player2Earnings;
    public static ArrayList<Integer> player1Level;
    public static ArrayList<Integer> player2Level;

    public static void run(Scanner scanner) {
        String input = "";

        while (true) {
            int mode = getMode(scanner);
            if (mode == 1) {
                twoPlayer(scanner);
            } else if (mode == 2) {

            } else {
                System.out.println("Please Buy Premium version . . .! ");
            }

        }
    }

    public static int getMode(Scanner scanner) {
        String input = "";
        System.out.println("Choose mode(just enter the name of the mode):");
        System.out.println("1.twoPlayer");
        System.out.println("2.Betting");
        input = scanner.nextLine();
        if (input.equals("twoPlayer")) {
            return 1;
        } else if (input.equals("Betting")) {
            return 2;
        }
        return 3;
    }

    public static void loginPlayer2(Scanner scanner) {
        user1 = User.getLoginUser();
        String input = "";
        String pass = "";
        System.out.println("player2 please enter your username :");
        input = scanner.nextLine();
        while (true) {
            System.out.println(input + " please enter your password :");
            pass = scanner.nextLine();
            if (User.getUserByName(input) != null) {
                if (User.getUserByName(input).getPassword().equals(pass)) {
                    System.out.println("player2 logged in successfully !");

                    user2 = User.getUserByName(input);
                    assert user2 != null;
                    if (user2.getMyCard().isEmpty()) {
                        Collections.shuffle(Main.cards);
                        for (int i = 0; i < 2; i++) {
                            user2.getMyCard().add(Main.cards.get(i));
                        }
                        System.out.println("New StarterPack With 20 Cards Is Now Available For You.");
                        System.out.println("You Can See Them On Profile Menu.");
                    }
                    break;
                } else {
                    System.out.println("Incorrect password !");
                }
            } else {
                System.out.println("There Is No User With This Username.");

            }
        }
    }

    public static void selectCharacters(Scanner scanner) {
        String character;
        System.out.println("P1 Select A Character:");
        System.out.println("1.Hacker");
        System.out.println("2.Police officer");
        System.out.println("3.Ninja");
        System.out.println("4.Hunter");

        character = scanner.nextLine();
        user1.setCharacter(character);
        System.out.println("Your Character Set Successfully!");
        boostCard(user1, user1.getCharacter());
        System.out.println("P2 Select A Character:");
        System.out.println("1.Hacker ");
        System.out.println("2.Police officer");
        System.out.println("3.Ninja");
        System.out.println("4.Hunter");
        character = scanner.nextLine();
        user2.setCharacter(character);
        boostCard(user2, user2.getCharacter());
        System.out.println("Your Character Set Successfully!");
    }

    //twoPlayer
    public static void twoPlayer(Scanner scanner) {
        loginPlayer2(scanner);
        selectCharacters(scanner);
        user1.createHand();
        user2.createHand();
        GameBoard gameBoard1 = new GameBoard();
        user1.setGameBoard(gameBoard1);
        GameBoard gameBoard2 = new GameBoard();
        user2.setGameBoard(gameBoard2);
        String input = "";
        selectStartingPlayer();
        int turn1 = 4, turn2 = 4; int p1PlayedRounds = 0, p2PlayedRounds = 0;
        while (true) {
            printDetails(user1, turn1);
            printHand(user1);
            user1.getGameBoard().displayGameBoard();
            System.out.println();
            user2.getGameBoard().displayGameBoard();
            printHand(user2);
            printDetails(user2, turn2);

            input = scanner.nextLine();
            if (RegexTypes.SELECTED_CARD.matches(input)) {
                if (Integer.parseInt(RegexTypes.SELECTED_CARD.getGroup(input, "usernumber")) == 1) {
                    printCardDetail(user1.getHand().get(Integer.parseInt(RegexTypes.SELECTED_CARD.getGroup(input, "number"))));
                } else {
                    printCardDetail(user2.getHand().get(Integer.parseInt(RegexTypes.SELECTED_CARD.getGroup(input, "number"))));
                }
            } else if (RegexTypes.PLACING_CARD.matches(input)) {
                if (player1turn && turn1 != 0) {
                    if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Shield")) {
                        user2.getGameBoard().blocks[Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))].setShow(false);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Heal")) {
                        user1.setHP(user1.getHP() + 30);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("PowerBooster")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user1.getGameBoard().addCardInBlock(i,c);
                        user1.getGameBoard().powerBooster();
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //buff
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("HoleChanger")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().holeChanger();
                        user2.getGameBoard().holeChanger();
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //change
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Repairer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().repairer(i);
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //repair
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("RoundReducer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        turn1--;
                        turn2--;
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardStealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.cards_4and6(user2, user2.getHand().get(i));
//                      user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //cardconceal
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardWeaker")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().cardWeaker();
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //cardweak
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardCopier")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.cards_6(user1.getHand().get(i));
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //copier
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Concealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //concealer
                    } else {
//                        int i = Integer.parseInt(Command.PLACING_CARD.getGroup(input, "blocknum"));
//                        Card c = user1.getHand().get(Integer.parseInt(Command.PLACING_CARD.getGroup(input, "number")));
//                        user1.getGameBoard().addCardInBlock(i-1, c);
//                        user1.getHand().remove(c);
//                        user1.completeHand();
//                        nobat = false;
//                        turn1--;
//                        user1.setDamage(user1.getDamage() + c.getDamage());
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
//                        if(user1.getGameBoard().isCanPlace(i-1,c)){ continue;}
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                    }
                } else if (!player1turn && turn2 != 0) {
                    if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Shield")) {
                        user1.getGameBoard().blocks[Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))].setShow(false);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Heal")) {
                        user2.setHP(user2.getHP() + 30);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("PowerBooster")) {

                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user1.getGameBoard().addCardInBlock(i,c);
                        user2.getGameBoard().powerBooster();
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //buff
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("HoleChanger")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().holeChanger();
                        user1.getGameBoard().holeChanger();
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //change
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Repairer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().repairer(i);
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //repair
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("RoundReducer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user1.getDamage() + c.getDamage());
                        turn1--;
                        turn2--;
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardStealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.cards_4and6(user1, user1.getHand().get(i));
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //cardconceal
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardWeaker")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().cardWeaker();
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //cardweak
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardCopier")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.cards_6(user2.getHand().get(i));
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //copier
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Concealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //concealer
                    } else {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
//                        if(user2.getGameBoard().isCanPlace(i-1,c)){ continue;}
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                    }
                }
                compareACC();
            }

            if (turn1 == 0 && turn2 == 0) {
                user1.setHP(user1.getHP() - user2.getDamage());
                user2.setHP(user2.getHP() - user1.getDamage());
                if (user1.getHP() != 0 && user2.getHP() != 0) {
                    turn1 = 4;
                    turn2 = 4;
                    user1.setDamage(0);
                    user2.setDamage(0);
                } else if (user1.getHP() == 0 && user2.getHP() != 0) {
                    System.out.println(user2.getUserName() + " Winner !");
                    nerfCard(user1, user1.getCharacter());
                    nerfCard(user2, user2.getCharacter());
                    int zarib = (int) (user2.getHP() + (user2.getDamage() - 100));
                    user2.setXP(user2.getXP() + (int) (0.6 * zarib));
                    user2.setCoin(user2.getCoin() + 0.8 * zarib);
                    user1.setXP(user1.getXP() - (int) (0.2 * user2.getDamage() - 100));
                    user2.levelUp();
                    user1.levelUp();
                    String formattedDateTime = currentDateTime.format(formatter);
                    player1Username.add(user1);
                    player2Username.add(user2);
                    player1GameState.add("Lost");
                    player2GameState.add("Win");
                    player1Level.add(user1.getLevel());
                    player2Level.add(user2.getLevel());
                    player1Earnings.add((int)(0.2 * user2.getDamage() - 100) + "XP");
                    player2Earnings.add("Earned: " + (int) (0.6 * zarib) + " XP" + "and " + user2.getCoin() + 0.8 * zarib + " Coin");
                    GameFinishedTime.add(formattedDateTime);
                    break;
                } else if (user1.getHP() != 0 && user2.getHP() == 0) {
                    System.out.println(user1.getUserName() + " Winner !");
                    nerfCard(user1, user1.getCharacter());
                    nerfCard(user2, user2.getCharacter());
                    double zarib = user1.getHP() + (user1.getDamage() - 100);
                    user1.setXP((int) (user1.getXP() + (int) (0.6 * zarib)));
                    user1.setCoin(user1.getCoin() + 0.8 * zarib);
                    user2.setXP(user2.getXP() - (int) (0.2 * user1.getDamage() - 100));
                    user1.levelUp();
                    user2.levelUp();
                    String formattedDateTime = currentDateTime.format(formatter);
                    player1Username.add(user1);
                    player2Username.add(user2);
                    player2GameState.add("Lost");
                    player1GameState.add("Win");
                    player1Level.add(user1.getLevel());
                    player2Level.add(user2.getLevel());
                    player2Earnings.add((int)(0.2 * user2.getDamage() - 100) + "XP");
                    player1Earnings.add("Earned: " + (int) (0.6 * zarib) + " XP" + "and " + user2.getCoin() + 0.8 * zarib + " Coin");
                    GameFinishedTime.add(formattedDateTime);
                    break;
                }
            }
        }

    }

    public void Betting(Scanner scanner) {
        loginPlayer2(scanner);
        selectCharacters(scanner);
        getCoin(scanner);
        user1.createHand();
        user2.createHand();
        GameBoard gameBoard1 = new GameBoard();
        user1.setGameBoard(gameBoard1);
        GameBoard gameBoard2 = new GameBoard();
        user2.setGameBoard(gameBoard2);
        String input = "";
        selectStartingPlayer();
        int turn1 = 4, turn2 = 4;  int p1PlayedRounds = 0, p2PlayedRounds = 0;
        while (true) {
            printDetails(user1, turn1);
            printHand(user1);
            user1.getGameBoard().displayGameBoard();
            System.out.println();
            user2.getGameBoard().displayGameBoard();
            printHand(user2);
            printDetails(user2, turn2);

            input = scanner.nextLine();
            if (RegexTypes.SELECTED_CARD.matches(input)) {
                if (Integer.parseInt(RegexTypes.SELECTED_CARD.getGroup(input, "usernumber")) == 1) {
                    printCardDetail(user1.getHand().get(Integer.parseInt(RegexTypes.SELECTED_CARD.getGroup(input, "number"))));
                } else {
                    printCardDetail(user2.getHand().get(Integer.parseInt(RegexTypes.SELECTED_CARD.getGroup(input, "number"))));
                }
            } else if (RegexTypes.PLACING_CARD.matches(input)) {
                if (player1turn && turn1 != 0) {
                    if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Shield")) {
                        user2.getGameBoard().blocks[Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))].setShow(false);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Heal")) {
                        user1.setHP(user1.getHP() + 30);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("PowerBooster")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user1.getGameBoard().addCardInBlock(i,c);
                        user1.getGameBoard().powerBooster();
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //buff
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("HoleChanger")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().holeChanger();
                        user2.getGameBoard().holeChanger();
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //change
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Repairer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().repairer(i);
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //repair
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("RoundReducer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        turn1--;
                        turn2--;
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardStealer")) { //KHARABE
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.cards_4and6(user2, user2.getHand().get(i));
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //cardconceal
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardWeaker")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().cardWeaker();
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //cardweak
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardCopier")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.cards_6(user1.getHand().get(i));
//                        user1.getGameBoard().addCardInBlock(i, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //copier
                    } else if (user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Concealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                        //concealer
                    } else {
//                        int i = Integer.parseInt(Command.PLACING_CARD.getGroup(input, "blocknum"));
//                        Card c = user1.getHand().get(Integer.parseInt(Command.PLACING_CARD.getGroup(input, "number")));
//                        user1.getGameBoard().addCardInBlock(i-1, c);
//                        user1.getHand().remove(c);
//                        user1.completeHand();
//                        nobat = false;
//                        turn1--;
//                        user1.setDamage(user1.getDamage() + c.getDamage());
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user1.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().addCardInBlock(i - 1, c);
//                        if(user1.getGameBoard().isCanPlace(i-1,c)){ continue;}
                        user1.getHand().remove(c);
                        user1.addAnotherCard(p1PlayedRounds);
                        p1PlayedRounds++;
                        player1turn = false;
                        turn1--;
                        user1.setDamage(user1.getDamage() + c.getDamage());
                    }
                } else if (!player1turn && turn2 != 0) {
                    if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Shield")) {
                        user1.getGameBoard().blocks[Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))].setShow(false);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Heal")) {
                        user2.setHP(user2.getHP() + 30);
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("PowerBooster")) {

                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user1.getGameBoard().addCardInBlock(i,c);
                        user2.getGameBoard().powerBooster();
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //buff
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("HoleChanger")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().holeChanger();
                        user1.getGameBoard().holeChanger();
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //change
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Repairer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().repairer(i);
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //repair
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("RoundReducer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user1.getDamage() + c.getDamage());
                        turn1--;
                        turn2--;
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardStealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.cards_4and6(user1, user1.getHand().get(i));
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //cardconceal
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardWeaker")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user1.getGameBoard().cardWeaker();
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //cardweak
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("CardCopier")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
//                        user2.getGameBoard().addCardInBlock(i, c);
                        user2.cards_6(user2.getHand().get(i));
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //copier
                    } else if (user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number"))).getName().equals("Concealer")) {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                        //concealer
                    } else {
                        int i = Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "blocknum"));
                        Card c = user2.getHand().get(Integer.parseInt(RegexTypes.PLACING_CARD.getGroup(input, "number")) - 1);
                        user2.getGameBoard().addCardInBlock(i - 1, c);
//                        if(user2.getGameBoard().isCanPlace(i-1,c)){ continue;}
                        user2.getHand().remove(c);
                        user2.addAnotherCard(p2PlayedRounds);
                        p2PlayedRounds++;
                        player1turn = true;
                        turn2--;
                        user2.setDamage(user2.getDamage() + c.getDamage());
                    }
                }
                compareACC();
            }

            if (turn1 == 0 && turn2 == 0) {
                user1.setHP(user1.getHP() - user2.getDamage());
                user2.setHP(user2.getHP() - user1.getDamage());
                if (user1.getHP() != 0 && user2.getHP() != 0) {
                    turn1 = 4;
                    turn2 = 4;
                    user1.setDamage(0);
                    user2.setDamage(0);
                } else if (user1.getHP() == 0 && user2.getHP() != 0) {
                    System.out.println(user2.getUserName() + " Winner !");
                    nerfCard(user1, user1.getCharacter());
                    nerfCard(user2, user2.getCharacter());
                    int zarib = (int) (user2.getHP() + (user2.getDamage() - 100));
                    user2.setXP(user2.getXP() + (int) (0.6 * zarib));
                    user2.setCoin(user2.getCoin() + bet);
                    user1.setCoin(user1.getCoin() - bet);
                    user1.setXP(user1.getXP() - (int) (0.2 * user2.getDamage() - 100));
                    user2.levelUp(user2.getXP());
                    user1.levelUp(user1.getXP());
                    String formattedDateTime = currentDateTime.format(formatter);
                    player1Username.add(user1);
                    player2Username.add(user2);
                    player1GameState.add("Lost");
                    player2GameState.add("Win");
                    player1Level.add(user1.getLevel());
                    player2Level.add(user2.getLevel());
                    player1Earnings.add((int)(0.2 * user2.getDamage() - 100) + "XP");
                    player2Earnings.add("Earned: " + (int) (0.6 * zarib) + " XP" + "and " + user2.getCoin() + 0.8 * zarib + " Coin");
                    GameFinishedTime.add(formattedDateTime);


                    break;
                } else if (user1.getHP() != 0 && user2.getHP() == 0) {
                    System.out.println(user1.getUserName() + " Winner !");
                    nerfCard(user1, user1.getCharacter());
                    nerfCard(user2, user2.getCharacter());
                    int zarib = (int) (user1.getHP() + (user1.getDamage() - 100));
                    user1.setXP(user1.getXP() + (int) (0.6 * zarib));
                    user1.setCoin(user1.getCoin() + bet);
                    user2.setCoin(user2.getCoin() - bet);
                    user1.levelUp(user1.getXP());
                    user2.levelUp(user2.getXP());
                    String formattedDateTime = currentDateTime.format(formatter);
                    player1Username.add(user1);
                    player2Username.add(user2);
                    player2GameState.add("Lost");
                    player1GameState.add("Win");
                    player1Level.add(user1.getLevel());
                    player2Level.add(user2.getLevel());
                    player2Earnings.add((int)(0.2 * user2.getDamage() - 100) + "XP");
                    player1Earnings.add("Earned: " + (int) (0.6 * zarib) + " XP" + "and " + user2.getCoin() + 0.8 * zarib + " Coin");
                    GameFinishedTime.add(formattedDateTime);

                    break;
                }
            }
        }

    }

    public static void boostCard(User user, String character) {
        for (Card card : user.getMyCard()) {
            if (Objects.equals(card.getType(), character)) {
                card.setACC(card.getACC() + 3);
                card.setDamage(card.getDamage() + 3);
            }
        }
    }

    public static void nerfCard(User user, String character) {
        for (Card card : user.getMyCard()) {
            if (Objects.equals(card.getType(), character)) {
                card.setACC(card.getACC() - 3);
                card.setDamage(card.getDamage() - 3);
            }
        }
    }

    public static void printDetails(User player, int turn) {
        System.out.println("Player: " + player.getUserName() + " HP: " + player.getHP() + " Damage: " + player.getDamage() + " Rounds Remaining: " + turn + " Character :" + player.getCharacterString());
    }

    public static void printHand(User user) {
        if (user.getHand() != null)
            for (Card c : user.getHand()) {
                System.out.println("Name :" + c.getName() + " Duration :" + c.getDuration() + " Damage :" + c.getDamage() + " ACC :" + c.getACC());
            }
    }

    public static void selectStartingPlayer() {
        Random random1 = new Random();
        int a = random1.nextInt(10);
        if (a < 8) {
            System.out.println("player1 start the game!");
            player1turn = true;
        } else {
            System.out.println("player2 start the game!");
            player1turn = false;
        }
    }

    public static void printCardDetail(Card c) {
        System.out.println("cardName :" + c.getName() + " Type :" + c.getType() + " Duration :" + c.getDuration() + " Damage :" + c.getDamage() + " ACC :" + c.getACC());

    }

    public static void compareACC() {
        for (int i = 0; i < 21; i++) {
            if (user1.getGameBoard().blocks[i].getCard() != null && user2.getGameBoard().blocks[i].getCard() != null && user1.getGameBoard().blocks[i].isShow() && user2.getGameBoard().blocks[i].isShow()) {
                if (user1.getGameBoard().blocks[i].getCard().getACC() > user2.getGameBoard().blocks[i].getCard().getACC()) {
                    user2.getGameBoard().blocks[i].setShow(false);
                    user2.setDamage(user2.getDamage() - user2.getGameBoard().blocks[i].getCard().getDamage());
                } else if (user1.getGameBoard().blocks[i].getCard().getACC() < user2.getGameBoard().blocks[i].getCard().getACC()) {
                    user1.getGameBoard().blocks[i].setShow(false);
                    user1.setDamage(user1.getDamage() - user1.getGameBoard().blocks[i].getCard().getDamage());
                } else {
                    user2.getGameBoard().blocks[i].setShow(false);
                    user1.getGameBoard().blocks[i].setShow(false);
                    user2.setDamage(user2.getDamage() - user2.getGameBoard().blocks[i].getCard().getDamage());
                    user1.setDamage(user1.getDamage() - user1.getGameBoard().blocks[i].getCard().getDamage());
                }
            }
        }

    }

    public void getCoin(Scanner scanner) {
        bet = 0;
        String input = "";
        System.out.println("Please enter the amount of money you want to bet:");
        while (true) {
            input = scanner.nextLine();
            if (RegexTypes.DIGIT.matches(input)) {
                if (user1.getCoin() >= Integer.parseInt(input) && user2.getCoin() >= Integer.parseInt(input)) {
                    bet = Integer.parseInt(input);
                    System.out.println("the bet set successfully");
                    break;
                } else {
                    System.out.println("One player doesn't have enough coin to bet!!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

}
