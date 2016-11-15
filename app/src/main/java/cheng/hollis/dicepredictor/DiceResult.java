package cheng.hollis.dicepredictor;

/**
 * Created by hollischeng on 15/11/2016.
 */

public class DiceResult {
    private int ThisRd1;
    private int ThisRd2;
    private int ThisRd3;

    public DiceResult(int thisRd1, int thisRd2, int thisRd3) {
        ThisRd1 = thisRd1;
        ThisRd2 = thisRd2;
        ThisRd3 = thisRd3;
    }

    @Override
    public String toString() {
        return "DiceResult{" +
                "ThisRd1=" + ThisRd1 +
                ", ThisRd2=" + ThisRd2 +
                ", ThisRd3=" + ThisRd3 +
                '}';
    }

    public int getThisRd1() {
        return ThisRd1;
    }

    public void setThisRd1(int thisRd1) {
        ThisRd1 = thisRd1;
    }

    public int getThisRd2() {
        return ThisRd2;
    }

    public void setThisRd2(int thisRd2) {
        ThisRd2 = thisRd2;
    }

    public int getThisRd3() {
        return ThisRd3;
    }

    public void setThisRd3(int thisRd3) {
        ThisRd3 = thisRd3;
    }
}