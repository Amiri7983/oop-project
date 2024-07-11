package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameBoard {
    private static final int BLOCK_COUNT = 21;
    public Cell[] blocks = new Cell[BLOCK_COUNT];
    private Random random = new Random();
    public boolean canPlace;

    public GameBoard() {
        initializeBlocks();
        removeRandomBlock();
    }

    private void initializeBlocks() {
        for (int i = 0; i < BLOCK_COUNT; i++) {
            blocks[i] = new Cell();
            blocks[i].setShow(true);
        }
    }

    private void removeRandomBlock() {
        int indexToRemove = random.nextInt(BLOCK_COUNT);
        if (indexToRemove >= 0 && indexToRemove < BLOCK_COUNT) {
            if (blocks[indexToRemove] != null) {
                blocks[indexToRemove].setShow(false);
            }
        }
    }

    public void displayGameBoard() {
        for (int i = 0; i < BLOCK_COUNT; i++) {
            if (blocks[i] != null) {
                if (blocks[i].isShow()) {
                    Card card = blocks[i].getCard();
                    if (card == null) {
                        System.out.print("[ ]   ");
                    } else {
                        System.out.print("[" + card.getName() + "]   ");
                    }
                } else {
                    System.out.print("[X]   ");
                }
            }
        }
        System.out.println();
    }

    public void addCardInBlock(int index, Card card) {
        boolean isValid = true;
        canPlace = true;
        if (index >= 0 && index < BLOCK_COUNT) {
            for (int i = index; i < index + card.getDuration() && i < BLOCK_COUNT; i++) {
                if (!blocks[i].isShow() || blocks[i].getCard() != null) {
                    isValid = false;
                    canPlace = false;
                }
            }
            if (isValid) {
                for (int i = index; i < index + card.getDuration() && i < BLOCK_COUNT; i++) {
                    if (!blocks[i].isShow()) {
                        System.out.println("Invalid input :) ");
                        canPlace = true;
                        return;
                    }
                    blocks[i].setCard(card);
                }
            }
        }
    }

    public boolean isCanPlace(int index, Card card) {
        if (index >= 0 && index < BLOCK_COUNT) {
            for (int i = index; i < index + card.getDuration() && i < BLOCK_COUNT; i++) {
                if (!blocks[i].isShow() || blocks[i].getCard() != null) {
                    System.out.println("not enough space to place this card!");
                    return false;
                }
            }
        }
        return true;
    }

    public void powerBooster() {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            if (blocks[i].getCard() != null) {
                indexList.add(i);
            }
        }
        Collections.shuffle(indexList);
        if (!indexList.isEmpty()) {
            int x = indexList.get(0);
            Card card = blocks[x].getCard();
            if (card != null) {
                card.setACC(card.getACC() + 5);
                card.setDamage(card.getDamage() + 5);
            }
        }
    }

    public void holeChanger() {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            if (blocks[i].getCard() == null && blocks[i].isShow()) {
                indexList.add(i);
            }
        }
        Collections.shuffle(indexList);
        if (!indexList.isEmpty()) {
            int x = indexList.get(0);
            blocks[x].setShow(false);
        }
    }

    public void repairer(int index) {
        if (index >= 0 && index < BLOCK_COUNT) {
            blocks[index].setShow(true);
        }
    }

    public void cardWeaker() {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < BLOCK_COUNT; i++) {
            if (blocks[i].getCard() != null) {
                indexList.add(i);
            }
        }
        Collections.shuffle(indexList);
        if (!indexList.isEmpty()) {
            int x = indexList.get(0);
            Card card = blocks[x].getCard();
            if (card != null) {
                card.setACC(card.getACC() - 5);
                card.setDamage(card.getDamage() - 5);
            }
        }
    }

    public Cell[] getBlocks() {
        return blocks;
    }

    public Random getRandom() {
        return random;
    }
}