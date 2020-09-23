package GameCenter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class board {
    int[][] index = {{31,32,33,34,35,36},
                    {25,26,27,28,29,30},
                    {24,23,22,21,20,19},
                    {13,14,15,16,17,18},
                    {12,11,10,9,8,7},
                    {1,2,3,4,5,6}};
    int[][] map = {{0,0,0,0,0,2},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {1,0,0,0,0,0}};
    ArrayList<SnakePlayer> players;
    public JButton[][] Bboard = new JButton[6][6];

    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel();
        fullboard.setLayout(new GridLayout(6,6));
        String text = "";
        for (int i = 0; i < 6; i++) {
            for(int j = 0; j<6; j++){
                text = "<html>"+index[i][j];
                switch(map[i][j]){
                    case 1:
                        text += "<br>Start";
                        break;
                    case 2:
                        text += "<br>Finish";
                        break;
                }
                text+= "</html>";

                Bboard[i][j] = new JButton(text);
                Bboard[i][j].setPreferredSize(new Dimension(50,50));
                fullboard.add(Bboard[i][j]);
            }
        }

        mainPanel.add(fullboard);
    }

    public void updateBoard(JFrame main) {
    }

    public void generateBoard() {
    }

    public void move(SnakePlayer p1, int index) {
        players.set(index, p1);

    }

    public void moveHandler() {
    }
}
