package GameCenter;

import javax.swing.*;
import java.awt.*;

public class board {
    int[][] index = {{31,32,33,34,35,36},
                    {25,26,27,28,29,30},
                    {24,23,22,21,20,19},
                    {13,14,15,16,17,18},
                    {12,11,10,9,8,7},
                    {1,2,3,4,5,6}};

    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel();
        fullboard.setLayout(new GridLayout(6,6));
        JButton[][] Bboard = new JButton[6][6];

        for (int i = 0; i < 6; i++) {
            for(int j = 0; j<6; j++){
                Bboard[i][j] = new JButton(String.valueOf(index[i][j]));
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

    public void move() {
    }

    public void moveHandler() {
    }
}
