package GameCenter;
import javax.swing.*;
import java.util.ArrayList;

public class SnakesLadders {
    public int[] wSize = {800, 900}; // 400, 450

    public ArrayList<ArrayList<Integer>> snakes;
    public ArrayList<ArrayList<Integer>> ladders;
    public ArrayList<SnakePlayer> players = new ArrayList<>();

    final public diceHandler dh = new diceHandler();
    final public board mainBoard = new board();
    int[] turnData = {0, 1}; // playerturn, maxplayers.
    boolean gameGoing = false;


    public SnakesLadders() {
        addPlayers();

        // mainframe
        JFrame mainframe = new JFrame("Snakes&Ladders");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainframe.setSize(wSize[0], wSize[1]);   // set window size
        mainframe.setLocationRelativeTo(null); // middle of screen
        mainframe.setLayout(null); // no layout manager
        mainframe.setResizable(true); // not resizable
        mainframe.setVisible(true); // is visable
        mainframe.setLayout(null);

        //toolbar
        JToolBar tb = new JToolBar();
        JButton tbStart = new JButton("Start");
        JButton tbReset = new JButton("Reset");
        JButton tbExit = new JButton("Exit");
        JButton addPlayer = new JButton("Add Player");

        tb.add(tbStart); tb.add(addPlayer);
        tb.add(tbReset); tb.add(tbExit);
        tb.setFloatable(false);
        tb.setBounds(0,0,400,20);
        mainframe.add(tb);

        // mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // board
        mainBoard.createBoard(mainPanel);

        // dice rolls
        JPanel dicePanel = new JPanel();
        JTextField d1 = new JTextField("Dice1");
        JTextField d2 = new JTextField("Dice2");
        JTextField sum = new JTextField("Sum:     ");
        sum.setBorder(BorderFactory.createEmptyBorder());
        JButton roll = new JButton("roll");

        d1.setEditable(false); d2.setEditable(false); sum.setEditable(false);
        dicePanel.add(d1); dicePanel.add(d2); dicePanel.add(sum);
        dicePanel.add(roll);

        mainPanel.add(dicePanel);

        // p1/p2 turn / info
        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.PAGE_AXIS));
        JTextField divInfo = new JTextField("");
        JTextField turnInfo = new JTextField("Player turn:     P1");

        divInfo.setBorder(BorderFactory.createEmptyBorder());
        divInfo.setSize(20,30);
        divInfo.setEditable(false);

        turnInfo.setBorder(BorderFactory.createEmptyBorder());
        turnInfo.setSize(20,30);
        turnInfo.setEditable(false);

        turnPanel.add(turnInfo); turnPanel.add(divInfo);
        mainPanel.add(turnPanel);

        // placement of mainPanel
        mainPanel.setBounds(0,20,wSize[0],wSize[0]-20);
        mainframe.add(mainPanel);

        // actionlisteners
        tbExit.addActionListener(e -> {
            mainframe.dispose();
        });
        roll.addActionListener(e-> {
            int[] s = dh.roll(d1, d2, sum, divInfo);
            takeTurn(s, divInfo, turnInfo);
        });
        addPlayer.addActionListener(e->{
            players.add(new SnakePlayer());
            turnData[1] += 1;
            divInfo.setText("Player " + (turnData[1] + 1) + " added!");
        });
        tbStart.addActionListener(e->{
            gameGoing = true;
            divInfo.setText("Game started, p1 click roll to begin");
        });

    }

    void addPlayers() {
        players.add(0, new SnakePlayer());
        players.add(1, new SnakePlayer());
    }

    public void takeTurn(int[] s, JTextField divInfo, JTextField turnInfo) {
        mainBoard.canClickLadder = false;
        if (gameGoing) {
            SnakePlayer sp = players.get(turnData[0]);

            if (!sp.moveHandler(s[2], divInfo)) {
                mainBoard.move(sp, turnData[0], divInfo);
                if (sp.x == 0 && sp.y == 0) {
                    divInfo.setText("Player " + (turnData[0] + 1) + " wins");
                    gameGoing = false;
                }
            }
            if (s[0] != s[1]) { // not roll again
                turnData[0] += 1; //  fix turn
                if (turnData[0] > turnData[1]) {
                    turnData[0] = 0;
                }
                turnInfo.setText("Player turn:     P" + (turnData[0] + 1));
            }
        } else {
            divInfo.setText("Game is not running");
        }
    }
}
