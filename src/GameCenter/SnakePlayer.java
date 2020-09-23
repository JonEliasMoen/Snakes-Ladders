package GameCenter;

import javax.swing.*;

public class SnakePlayer {
    public int x = 0; // index
    int y = 5;

    public void setPos(int X, int Y){
        x = X; y = Y;
    }
    public boolean outside(int sum){
        return (x+sum)>5 && y == 0;
    }
    public boolean moveHandler(int sum, JTextField divInfo){
        if(!outside(sum)){
            calcPos(sum);
            return false;
        }else{
            divInfo.setText("Move goes above the finish line");
            return true;
        }
    }
    public void calcPos(int sum){ // x=5, sum = 12,
        x += sum;
        while(x>5){ x -= 5; y -= 1; }
    }
}

