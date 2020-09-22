package GameCenter;

import javax.swing.*;
import java.awt.*;

public class board {


    public void createBoard(JPanel mainPanel) {
        JPanel[] fullboard = new JPanel[6];
        JButton[][] Bboard = new JButton[6][6];

        for (int i = 0; i < 6; i++) {
            fullboard[i] = new JPanel();
            for (int j = 0; j < 6; j++) {
                Bboard[i][j] = new JButton(String.valueOf((5 - i) * 6 + (j + 1)));
                Bboard[i][j].setPreferredSize(new Dimension(50,30));
                fullboard[i].add(Bboard[i][j]);
            }
            mainPanel.add(fullboard[i]);
        }
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
