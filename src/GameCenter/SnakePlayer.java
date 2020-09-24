package GameCenter;

import javax.swing.*;

public class SnakePlayer {
    public int x = 0; // index
    public int y = 5;

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
    public void calcPosTest(){
        System.out.println("x="+x);calcPos(12);
        System.out.println("x="+x);
    }
    public void calcPos(int sum){ // x=5, sum = 12,
        x += sum*((y%2 == 0)? -1 : 1);
        while(x>5 || x<0){
            x = ((y%2 == 0)? (x*-1-1) : 6-(x-5));
            y -= 1;
        }
    }
}

