package GameCenter;

import javax.swing.*;

public class SnakePlayer {
    public int x = 0; // index
    public int y = 5;
    public int ox = 0;
    public int oy = 0;
    public int index = 0;

    public void setPos(int X, int Y, boolean ou){
        if(ou){
            ox = x; oy = y;
        }
        x = X; y = Y;
    }
    public boolean outside(int sum){
        return (x-sum)<0 && y == 0 || (y == 1 && 6-(x+sum-5)<0);
    }
    public boolean moveHandler(int sum, JTextField divInfo){
        ox = x; oy = y;
        if(calcPos(sum)){
            divInfo.setText("Move goes above the finish line");
            return true;
        }
        return false;
    }
    public boolean calcPos(int sum){ // 6-((x+s)%6), (x+s)%6 også mulig
        if(!outside(sum)) {
            x += sum * ((y % 2 == 0) ? -1 : 1);
            while (x > 5 || x < 0) {
                x = ((y % 2 == 0) ? (x * -1 - 1) : 6 - (x - 5));
                y -= 1;
            }
            return false;
        }else{
            return true;
        }

    }
}

