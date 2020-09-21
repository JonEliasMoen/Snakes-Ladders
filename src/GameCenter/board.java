package GameCenter;

import javax.swing.*;
import java.awt.*;

public class board {
    public JButton[][] Bboard = new JButton[6][6];

    {
    }


    public void createBoard(JFrame main) {
        JPanel bPanel = new JPanel();
        int value = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                value = (5 - i) * 6 + (j + 1);
                Bboard[i][j] = new JButton(String.valueOf(value));
                bPanel.add(Bboard[i][j]);
            }
        }
        main.add(bPanel, BorderLayout.CENTER);
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
