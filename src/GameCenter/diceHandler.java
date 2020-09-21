package GameCenter;

import javax.swing.*;
import java.lang.Math;
public class diceHandler{
    int[] dice = {0,0};
    public void roll(JTextField d1){
        d1.setText(String.valueOf(Math.random()*6));
    }
}
