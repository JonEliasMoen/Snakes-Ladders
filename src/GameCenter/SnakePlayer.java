package GameCenter;

import javax.swing.*;

public class SnakePlayer {
    public int x = 0; // index
    public int y = 5;
    public int ox = 0;
    public int oy = 0;
    public int index = 0;

    public void setPos(int X, int Y){ // set position of player
        ox = x; oy = y; // update old position
        x = X; y = Y; // set new position
    }
    public boolean outside(int sum){ // test whether will go outside the board.
        return (x-sum)<0 && y == 0 || (y == 1 && 6-(x+sum-5)<0);
    }
    public boolean moveHandler(int sum, JTextField divInfo){ // handle moving of player
        ox = x; oy = y;
        if(calcPos(sum)){ // calculate new position
            divInfo.setText("Move goes above the finish line");
            return true;
        }
        return false;
    }
    public boolean calcPos(int sum){ // could also use mod
        if(!outside(sum)) {
            x += sum * ((y % 2 == 0) ? -1 : 1); // x position go left or right direction
            while (x > 5 || x < 0) { // while X is out side board parameters 0-5
                x = ((y % 2 == 0) ? (x * -1 - 1) : 6 - (x - 5));  // calculate X for the line above
                y -= 1; // subtract y
            }
            return false; // good move
        }else{
            return true; // illegal move. goes beyond finishline
        }

    }
}

