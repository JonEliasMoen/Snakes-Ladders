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
    int[][] map = {{2,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {1,0,0,0,0,0}};
    ArrayList<SnakePlayer> players;
    public JButton[][] Bboard = new JButton[6][6];

    public String getString(int x, int y){
        String text = "<html>"+index[y][x];
        switch(map[y][x]){
            case 1:
                text += "<br>Start";
                break;
            case 2:
                text += "<br>Finish";
                break;
        }
        return text;
    }
    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel();
        fullboard.setLayout(new GridLayout(6,6));
        for (int i = 0; i < 6; i++) {
            for(int j = 0; j<6; j++){
                Bboard[i][j] = new JButton(getString(j, i)+"</html>");
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
        Bboard[p1.y][p1.x].setText(getString(p1.y, p1.x)+"<br>P"+index+"</html>");

    }

    public void moveHandler() {
    }
}
