package GameCenter;

import javax.swing.*;
import static java.lang.Math.round;
public class diceHandler {
    public int[] dice = {0, 0, 0};
    public final int MAX = 6;

    public int[] roll(JTextField d1, JTextField d2, JTextField sum, JTextField divInfo) {
        Dice d = new Dice();
        divInfo.setText("");
        for(int i = 0; i<2; i++){
            dice[i] = d.Roll();
        }
        dice[2] = dice[0]+dice[1];
        if(dice[0] == dice[1]){
            divInfo.setText("Roll again!");
        }
        d1.setText(String.valueOf(dice[0]));
        d2.setText(String.valueOf(dice[1]));
        sum.setText("Sum: "+ dice[2]);
        return dice;
    }
}
