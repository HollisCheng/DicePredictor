package cheng.hollis.dicepredictor;

/**
 * Created by hollischeng on 15/11/2016.
 */

public class DiceObject {
    private int DiceNumber;
    private int BecomeOne;
    private int BecomeTwo;
    private int BecomeThree;
    private int BecomeFour;
    private int BecomeFive;
    private int BecomeSix;

    public DiceObject(int diceNumber, int becomeOne, int becomeTwo, int becomeThree, int becomeFour, int becomeFive, int becomeSix) {
        DiceNumber = diceNumber;
        BecomeOne = becomeOne;
        BecomeTwo = becomeTwo;
        BecomeThree = becomeThree;
        BecomeFour = becomeFour;
        BecomeFive = becomeFive;
        BecomeSix = becomeSix;
    }

    @Override
    public String toString() {
        return "DiceObject{" +
                "DiceNumber=" + DiceNumber +
                ", BecomeOne=" + BecomeOne +
                ", BecomeTwo=" + BecomeTwo +
                ", BecomeThree=" + BecomeThree +
                ", BecomeFour=" + BecomeFour +
                ", BecomeFive=" + BecomeFive +
                ", BecomeSix=" + BecomeSix +
                '}';
    }

    public int getDiceNumber() {
        return DiceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        DiceNumber = diceNumber;
    }

    public int getBecomeOne() {
        return BecomeOne;
    }

    public void setBecomeOne(int becomeOne) {
        BecomeOne = becomeOne;
    }

    public int getBecomeTwo() {
        return BecomeTwo;
    }

    public void setBecomeTwo(int becomeTwo) {
        BecomeTwo = becomeTwo;
    }

    public int getBecomeThree() {
        return BecomeThree;
    }

    public void setBecomeThree(int becomeThree) {
        BecomeThree = becomeThree;
    }

    public int getBecomeFour() {
        return BecomeFour;
    }

    public void setBecomeFour(int becomeFour) {
        BecomeFour = becomeFour;
    }

    public int getBecomeFive() {
        return BecomeFive;
    }

    public void setBecomeFive(int becomeFive) {
        BecomeFive = becomeFive;
    }

    public int getBecomeSix() {
        return BecomeSix;
    }

    public void setBecomeSix(int becomeSix) {
        BecomeSix = becomeSix;
    }
}
