package GameCenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SnakeBoard {
    int[][] index = {{36, 35, 34, 33, 32, 31}, // index of every tile
            {25, 26, 27, 28, 29, 30},
            {24, 23, 22, 21, 20, 19},
            {13, 14, 15, 16, 17, 18},
            {12, 11, 10, 9, 8, 7},
            {1, 2, 3, 4, 5, 6}};
    int[][] map = {{2, -1, 0, 0, 0, 0}, // map. 1=start 2=finish
            {0, -1, 0, 0, 0, 0}, // negative integers = snake
            {0, 0, 0, 0, 0, 0}, // positive integers > 2 = ladder
            {0, 0, 0, 0, 0, 0}, // has one snake placed. to counter deadlock situation.
            {0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0}};

    ArrayList<Integer[]> playerPos = new ArrayList<>(); // holds position of players.
    public JButton[][] Bboard = new JButton[6][6]; // button array

    public int Aladders = 4; // number of snakes and ladders
    public int Asnakes = 3;
    ImageIcon ladderIc; // icon for snake and ladder
    ImageIcon snakeIc;

    SnakeBoard() {
        ladderIc = new ImageIcon("./src/GameCenter/index.jpg");
        snakeIc = new ImageIcon("./src/GameCenter/snake.png");
    }

    public int[][] blankSq() { // find 2 blank squares on map to use.
        int x = (int) (Math.random() * 5);
        int y = (int) (Math.random() * 5);
        int x2 = (int) (Math.random() * 5);
        int y2 = (int) (Math.random() * 5);

        while (map[y][x] != 0) { // generate new x,y until empty
            y = (int) (Math.random() * 5);
            x = (int) (Math.random() * 5);
        }
        while ((map[y2][x2] != 0) || (y2 == y)) { // generate new x2,y2 until empty
            y2 = (int) (Math.random() * 5); // cant have ladder on same colomn,
            x2 = (int) (Math.random() * 5); // aka y must not be equal to y2
        }
        if ((map[y][x] + map[y2][x2] != 0)) {
            blankSq(); // in case of permutation error
        }
        return new int[][]{{x, y}, {x2, y2}};
    }
    public void generateBoard(){ // generate random snake ladder board within parameters
        int sc = 0; // index to use for snake
        int lc = 2; // index to use for ladder.
        int[][] xy;
        while(lc != Aladders+2){ // ladders
            xy = blankSq(); // 2 new blank squares. {x,y}
            lc += 1; // increment ladderindex
            map[xy[0][1]][xy[0][0]] = lc;
            map[xy[1][1]][xy[1][0]] = lc; // set index
        }
        while(sc != Asnakes){ // snakes
            xy = blankSq();
            sc += 1;
            map[xy[0][1]][xy[0][0]] = -sc;
            map[xy[1][1]][xy[1][0]] = -sc;
        }

    }
    public String playerString(int x, int y){ // get player string for board index x,y
        Integer[] item;
        Integer[] pos = {x, y};
        String text = "";

        for(int i = 0; i<playerPos.size(); i++){ // had issues with find functionality of arraylist
            item = playerPos.get(i); // get position of player i
            if((item[0].equals(pos[0])) && (item[1].equals(pos[1]))){ // this tile has a player on it
                text += "<br>P"+(i + 1); // add to text
            }
        }
        return text; // return text
    }
    public String getString(int x, int y){
        String text = "<html>" + index[y][x]; // get number of tile
        String pText = playerString(x,y); //get string of player
        switch (map[y][x]) {
            case 1:
                text += "<br>Start"; // set start and finish text
                break;
            case 2:
                text += "<br>Finish";
                break;
        }
        text += pText;
        return text + "</html>"; // return
    }
    public void setProperties(int x, int y, ImageIcon ld, ImageIcon sk){
        if(map[y][x] > 2){ // ladder
            Bboard[y][x].setIcon(ld); // set icon of ladder
        }
        if(map[y][x] < 0){ // snake
            Bboard[y][x].setIcon(sk); // set icon of snake
        }
        if(playerString(x, y) != ""){
            Bboard[y][x].setBackground(new Color(0,125,0)); // set green color of player tile
        }else{
            Bboard[y][x].setBackground(new Color(255,255,255));
        }
    }
    public void createBoard(JPanel mainPanel) {
        JPanel fullboard = new JPanel(); // panel for the board
        fullboard.setLayout(new GridLayout(6,6)); // use gridlayout

        generateBoard(); // generate random snakeladder map

        for (int i = 0; i < 6; i++) { // BBoard
            for(int j = 0; j<6; j++){
                Bboard[i][j] = new JButton(getString(j,i)); // new button
                Bboard[i][j].setPreferredSize(new Dimension(75, 75)); // set size
                Bboard[i][j].setBackground(new Color(255,255,255)); // set white background
                Bboard[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,10)); // set font
                setProperties(j, i, ladderIc, snakeIc); // set properties of button
                fullboard.add(Bboard[i][j]); // add to fullbard.
            }
        }
        mainPanel.add(fullboard); // add to mail panel
    }
    public int[] move(SnakePlayer p1, int index, JTextField divInfo) {
        int i = 0, j = 0;
        int[] retdata = new int[]{-1,0,0,0,0};
        boolean going = true;

        if(map[p1.y][p1.x] < 0){ // hit snake, top or bottom
            for(i = p1.y; i<6 && going; i++){ // find other end of snake
                for(j = 0; j<6 && going; j++){
                    if(map[i][j] == map[p1.y][p1.x] && p1.y != i){
                        going = false; // stop nested for loop
                        divInfo.setText("P" + (index + 1) + " hit snake, click to go down");
                        retdata = new int[]{index, j, i, p1.x, p1.y}; // return specialmove data
                    }
                }
            }
        }
        if(map[p1.y][p1.x] > 2){ // hit ladder, top or bottom
            for(i = 0; i<p1.y && going; i++){ // find other end.
                for(j = 0; j<6 && going; j++){
                    if(map[i][j] == map[p1.y][p1.x] && p1.y != i){
                        going = false; // stop looking
                        divInfo.setText("P" + (index + 1) + " hit ladder, click to go up");
                        retdata = new int[]{index, j, i, p1.x, p1.y}; // specialmove return data
                    }
                }
            }
        }
        Integer[] pos = {p1.x, p1.y}; // Integer array to add to position

        if (playerPos.size() < index + 1) { // first time this user is here
            playerPos.add(index, pos);
        } else { // has this index already.
            playerPos.set(index, pos);
        }

        if (p1.x > -1 && p1.y > -1 && p1.ox > -1 && p1.oy > -1) { // move the text
            Bboard[p1.oy][p1.ox].setText(getString(p1.ox, p1.oy)); // set old position
            Bboard[p1.y][p1.x].setText(getString(p1.x, p1.y)); // set new position
            setProperties(p1.x, p1.y, ladderIc, snakeIc); // set properties
            setProperties(p1.ox, p1.oy, ladderIc, snakeIc);
        }else{ // something is terribly wrong with the program
            System.out.println("move error " + p1.ox + " "+ p1.oy + " "+ p1.x + " "+ p1.y);
        }
        return retdata; // return data
    }
}
