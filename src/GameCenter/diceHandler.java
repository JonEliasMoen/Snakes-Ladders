package GameCenter;

import javax.swing.*;
public class diceHandler {
    int[] dice = {0, 0, 0};

    public void roll(JTextField d1, JTextField d2) {
        d1.setText(String.valueOf(Math.random() * 6));
    }
}
