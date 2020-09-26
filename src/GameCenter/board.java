package GameCenter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Color;
import java.lang.Math;


public class board {
    int[][] index =    {{36,35,34,33,32,31},
                        {25,26,27,28,29,30},
                        {24,23,22,21,20,19},
                        {13,14,15,16,17,18},
                        {12,11,10,9,8,7},
                        {1,2,3,4,5,6}};
    int[][] map =  {{2, 0, 0, 0, 0, 0}, // Arrays.asList(array).indexOf(4);
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0}};
    ArrayList<Integer[]> playerPos = new ArrayList<>();

    ArrayList<Integer[]> snakes = new ArrayList<>();
    ArrayList<Integer[]> ladders = new ArrayList<>();

    public JButton[][] Bboard = new JButton[6][6];
    public int Aladders = 2;
    public int Asnakes = 2;

    public int[][] blankSq(){
        int x = 0, y = 0;
        int x2 = 0, y2 = 0;
        while(map[y][x] != 0){
            y = (int) (Math.random()*5);
            x = (int) (Math.random()*5);
        }
        while(map[y2][x2] != 0){
            while(y2 == y) {
                y2 = (int) (Math.random() * 5);
            }
            x2 = (int) (Math.random()*5);
        }
        return new int[][]{{x,y}, {x2,y2}};
    }
    public void generateBoard(){
        int sc = 0;
        int lc = 2;
        int[][] xy = new int[2][2];
        while(lc != Aladders+2){
            xy = blankSq();
            lc += 1;
            map[xy[0][1]][xy[0][0]] = lc;
            map[xy[1][1]][xy[1][0]] = lc;
        }
        while(sc != Asnakes){
            xy = blankSq();
            sc += 1;
            map[xy[0][1]][xy[0][0]] = -sc;
            map[xy[1][1]][xy[1][0]] = -sc;
        }

    }
    public int playerIndex(int x, int y){
        Integer[] item;
        Integer[] pos = {x, y};
        for(int i = 0; i<playerPos.size(); i++){
            item = playerPos.get(i);
            if((item[0] == pos[0]) && (item[1] == pos[1])){
                return (i + 1);
            }
        }
        return -1;
    }
    public String getString(int x, int y){ // TODO: color each player?
        String text = "<html>" + index[y][x];
        int pIndex = playerIndex(x,y);
        switch (map[y][x]) {
            case 1:
                text += "<br>Start";
                break;
            case 2:
                text += "<br>Finish";
                break;
        }
        if(pIndex != -1){
            text += "<br>P" + pIndex;
        }
        return text + "</html>";
    }
    public void setProperties(JButton bt, int x, int y, ImageIcon ld, ImageIcon sk){
        if(map[y][x] > 2){ // ladder
            Bboard[y][x].setIcon(ld);
            Bboard[y][x].setBackground(new Color(0,(int) map[y][x]*125/Aladders,0));
        }
        if(map[y][x] < 0){ // snake
            Bboard[y][x].setIcon(sk);
            Bboard[y][x].setBackground(new Color((int) Math.abs(map[y][x]*125/Asnakes),0,0));
        }
    }
    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel();
        ImageIcon ladder = new ImageIcon("C:/Users/jon39/IdeaProjects/Snakes-Ladders/src/GameCenter/index.jpg");
        ImageIcon snake = new ImageIcon("C:/Users/jon39/IdeaProjects/Snakes-Ladders/src/GameCenter/snake.png");
        fullboard.setLayout(new GridLayout(6,6));

        generateBoard();

        for (int i = 0; i < 6; i++) {
            for(int j = 0; j<6; j++){
                Bboard[i][j] = new JButton(getString(j,i));
                Bboard[i][j].setPreferredSize(new Dimension(100, 100));
                Bboard[i][j].setBackground(new Color(255,255,255));
                setProperties(Bboard[i][j], j, i, ladder, snake);

                fullboard.add(Bboard[i][j]);
            }
        }
        Bboard[3][3].setIcon(ladder);
        mainPanel.add(fullboard);
    }

    public void updateBoard(JFrame main) {
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
