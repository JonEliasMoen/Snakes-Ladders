package GameCenter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SnakesLadders {
    public int boardButtonSize = 10; // board button
    public int[] wSize = {400, 400}; // hver celle er en classe eller struct?
    public double[][] board = {{0, 0, 0, 0, 0, -3}, // -1.2 finish, -1.1 start
            {0, 0, 0, 0, 0, 0}, // 1-n player n
            {0, 0, 0, 0, 0, 0}, //
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {-2, 0, 0, 0, 0, 0}};

    public ArrayList<ArrayList<Integer>> snakes;
    public ArrayList<ArrayList<Integer>> ladders;


    final public diceHandler dh = new diceHandler();
    final public board mainBoard = new board();

    public SnakesLadders() {
        JFrame mainframe = new JFrame("Snakes&Ladders");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainframe.setSize(400, 400);   // set window size
        mainframe.setLocationRelativeTo(null); // middle of screen
        mainframe.setLayout(null); // no layout manager
        mainframe.setResizable(false); // not resizable
        mainframe.setVisible(true); // is visable
        mainframe.setLayout(new BorderLayout());

        //toolbar
        JToolBar tb = new JToolBar();
        JPanel tbPanel = new JPanel();
        JButton tbStart = new JButton("Start");
        JButton tbReset = new JButton("Reset");
        JButton tbExit = new JButton("Exit");
        tbPanel.add(tbStart);
        tbPanel.add(tbReset);
        tbPanel.add(tbExit);
        tb.add(tbPanel);
        tb.setFloatable(false);
        mainframe.add(tb, BorderLayout.NORTH);

        // dice rolls
        int dOffset = 25;
        int diceSize = 30;
        int diceY = 100;
        JPanel dicePanel = new JPanel();
        JTextField d1 = new JTextField("D1");
        JTextField d2 = new JTextField("D2");
        JTextField sum = new JTextField("sum:     ");
        sum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JButton roll = new JButton("roll");


        d1.setBounds(wSize[0] / 4 - dOffset, diceY, diceSize, diceSize);
        d2.setBounds(wSize[0] / 2 - dOffset, diceY, diceSize, diceSize);
        sum.setBounds(wSize[0] / 2 + 10, diceY, 60, 30);
        roll.setBounds(3 * wSize[0] / 4 - dOffset, diceY, 75, diceSize);

        d1.setEditable(false);
        d2.setEditable(false);
        sum.setEditable(false);
        dicePanel.add(d1);
        dicePanel.add(d2);
        dicePanel.add(sum);

        dicePanel.add(roll);
        mainframe.add(dicePanel, BorderLayout.CENTER);

        mainBoard.createBoard(mainframe);

        // p1/p2 turn
        JTextArea turnInfo = new JTextArea("player x");

        //mainframe.add(turnInfo);

        // actionlisteners
        tbExit.addActionListener(e -> {
            mainframe.dispose();
        });


    }
}
