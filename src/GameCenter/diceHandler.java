package GameCenter;

import javax.swing.*;
import java.lang.Math;
public class diceHandler{
    public void roll(JTextField d1){
        d1.setText(String.valueOf(Math.random()*6)+1);
    }
}
