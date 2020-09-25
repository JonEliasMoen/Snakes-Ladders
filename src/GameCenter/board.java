package GameCenter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class board {
    int[][] index = {{36,35,34,33,32,31},
                    {25,26,27,28,29,30},
                    {24,23,22,21,20,19},
                    {13,14,15,16,17,18},
                    {12,11,10,9,8,7},
                    {1,2,3,4,5,6}};
    int[][] map = {{2, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0}};
    ArrayList<Integer[]> playerPos = new ArrayList<>();
    public JButton[][] Bboard = new JButton[6][6];

    public String getString(int x, int y){ // TODO: color each player?
        Integer[] pos = {x, y};
        Integer[] item;
        String text = "<html>" + index[y][x];
        switch (map[y][x]) {
            case 1:
                text += "<br>Start";
                break;
            case 2:
                text += "<br>Finish";
                break;
        }
        for(int i = 0; i<playerPos.size(); i++){
            item = playerPos.get(i);
            if((item[0] == pos[0]) && (item[1] == pos[1])){
                text += "<br>P" + (i + 1);
            }
        }
        return text + "</html>";
    }
    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel();
        ImageIcon ladder = new ImageIcon("C:/Users/jon39/IdeaProjects/Snakes-Ladders/src/GameCenter/index.jpg");
        fullboard.setLayout(new GridLayout(6,6));
        for (int i = 0; i < 6; i++) {
            for(int j = 0; j<6; j++){
                Bboard[i][j] = new JButton(getString(j,i));
                Bboard[i][j].setPreferredSize(new Dimension(50, 50));

                fullboard.add(Bboard[i][j]);
            }
        }
        Bboard[3][3].setIcon(ladder);
        mainPanel.add(fullboard);
    }

    public void updateBoard(JFrame main) {
    }

    public void generateBoard() {
    }

    public void move(SnakePlayer p1, int index) {
        Integer[] pos = {p1.x, p1.y};

        if (playerPos.size() < index + 1) {
            playerPos.add(index, pos);
        } else {
            playerPos.set(index, pos);
        }

        if (p1.x > -1 && p1.y > -1) {
            Bboard[p1.oy][p1.ox].setText(getString(p1.ox, p1.oy));
            Bboard[p1.y][p1.x].setText(getString(p1.x, p1.y));
        }
    }

    public void moveHandler() {
    }
}
