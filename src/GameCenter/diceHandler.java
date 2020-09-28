package GameCenter;

import javax.swing.*;
public class diceHandler {
    public int[] dice = {0, 0, 0}; // d1, d2, sum
    public int[] roll(JTextField d1, JTextField d2, JTextField sum, JTextField divInfo) {
        Dice d = new Dice(); // new dice
        divInfo.setText(""); // reset divinfo
        for (int i = 0; i < 2; i++) {
            dice[i] = d.Roll(); // roll twice
        }
        dice[2] = dice[0] + dice[1]; // calculate sum
        if (dice[0] == dice[1]) {
            divInfo.setText("Roll again"); // set roll again text
        }

        d1.setText(String.valueOf(dice[0])); // set dice text
        d2.setText(String.valueOf(dice[1]));
        sum.setText("Sum: "+ dice[2]); // set sum text
        return dice; // return dice array
    }
}
