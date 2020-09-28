package GameCenter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Color;
import java.lang.Math;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class board{
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
    public JButton[][] Bboard = new JButton[6][6];
    Boolean canClickLadder = false;

    public int Aladders = 4;
    public int Asnakes = 4;
    ImageIcon ladderIc;
    ImageIcon snakeIc;

    board(){
        ladderIc = new ImageIcon("C:/Users/jon39/IdeaProjects/Snakes-Ladders/src/GameCenter/index.jpg");
        snakeIc = new ImageIcon("C:/Users/jon39/IdeaProjects/Snakes-Ladders/src/GameCenter/snake.png");
    }

    public int[][] blankSq(){
        int x =  (int) (Math.random()*5);
        int y =  (int) (Math.random()*5);
        int x2 = (int) (Math.random()*5);
        int y2 = (int) (Math.random()*5);

        while(map[y][x] != 0 || (x == 0 && y==0)){
            y = (int) (Math.random()*5);
            x = (int) (Math.random()*5);
        }
        while((map[y2][x2] != 0) || (y2 == y)){
            y2 = (int) (Math.random() * 5);
            x2 = (int) (Math.random()*5);
        }
        if((map[y][x] + map[y2][x2] != 0)){
            blankSq();
        }
        System.out.println(map[y][x]+" "+map[y2][x2]+" "+(y2==y));
        return new int[][]{{x,y}, {x2,y2}};
    }
    public void generateBoard(){
        int sc = 0;
        int lc = 2;
        int[][] xy = new int[2][2];
        while(lc != Aladders+2){ // ladders
            xy = blankSq();
            lc += 1;
            map[xy[0][1]][xy[0][0]] = lc; // {x,y}
            map[xy[1][1]][xy[1][0]] = lc;
        }
        while(sc != Asnakes){ // snakes
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
    public void setProperties(int x, int y, ImageIcon ld, ImageIcon sk){
        if(map[y][x] > 2){ // ladder
            Bboard[y][x].setIcon(ld);
        }
        if(map[y][x] < 0){ // snake
            Bboard[y][x].setIcon(sk);
        }
        if(playerIndex(x, y) != -1){
            Bboard[y][x].setBackground(new Color(0,125,0));
        }else{
            Bboard[y][x].setBackground(new Color(255,255,255));
        }
    }
    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel();
        fullboard.setLayout(new GridLayout(6,6));

        generateBoard();

        for (int i = 0; i < 6; i++) {
            for(int j = 0; j<6; j++){
                Bboard[i][j] = new JButton(getString(j,i));
                Bboard[i][j].setPreferredSize(new Dimension(100, 100));
                Bboard[i][j].setBackground(new Color(255,255,255));
                setProperties(j, i, ladderIc, snakeIc);
                fullboard.add(Bboard[i][j]);
            }
        }
        mainPanel.add(fullboard);
    }
    public int[] move(SnakePlayer p1, int index, JTextField divInfo, boolean jump) {
        int i = 0, j = 0;
        int[] retdata = new int[]{-1,0,0,0,0};
        boolean going = true;

        if(map[p1.y][p1.x] < 0){ // hit snake, top or bottom
            for(i = p1.y; i<6 && going; i++){
                for(j = 0; j<6 && going; j++){
                    if(map[i][j] == map[p1.y][p1.x] && p1.y != i){
                        going = false;
                        divInfo.setText("P" + (index + 1) + " hit snake, click to go down");
                        retdata = new int[]{index, i, j, p1.x, p1.y};
                        //p1.setPos(j, i, true);
                    }
                }
            }
        }
        if(map[p1.y][p1.x] > 2){ // hit snake, top or bottom
            for(i = 0; i<p1.y && going; i++){
                for(j = 0; j<6 && going; j++){
                    if(map[i][j] == map[p1.y][p1.x] && p1.y != i){
                        going = false;
                        divInfo.setText("P" + (index + 1) + " hit ladder, click to go up");
                        System.out.println(Bboard[0][3].getText());
                        retdata = new int[]{index, j, i, p1.x, p1.y};
                    }
                }
            }
        }

        Integer[] pos = {p1.x, p1.y};

        if (playerPos.size() < index + 1) {
            playerPos.add(index, pos);
        } else {
            playerPos.set(index, pos);
        }

        if (p1.x > -1 && p1.y > -1 && p1.ox > -1 && p1.oy > -1) { // move the text
            Bboard[p1.oy][p1.ox].setText(getString(p1.ox, p1.oy));
            Bboard[p1.y][p1.x].setText(getString(p1.x, p1.y));
            setProperties(p1.x, p1.y, ladderIc, snakeIc);
            setProperties(p1.ox, p1.oy, ladderIc, snakeIc);
        }else{
            System.out.println("move error " + p1.ox + " "+ p1.oy + " "+ p1.x + " "+ p1.y);
        }
        return retdata;
    }

    public void moveHandler() {
    }
}
