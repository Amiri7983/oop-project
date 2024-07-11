package logic;

public class Cell {
    private boolean show ;
    private Card card;

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isShow() {
        return show;
    }

    public Card getCard() {
        return card;
    }

    public boolean getACC() {
        return false;
    }
}
