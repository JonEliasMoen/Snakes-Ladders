package GameCenter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SnakesLadders {
    public int[] wSize = {400, 450}; // hver celle er en classe eller struct?
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
        // mainframe
        JFrame mainframe = new JFrame("Snakes&Ladders");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainframe.setSize(wSize[0], wSize[1]);   // set window size
        mainframe.setLocationRelativeTo(null); // middle of screen
        mainframe.setLayout(null); // no layout manager
        mainframe.setResizable(false); // not resizable
        mainframe.setVisible(true); // is visable
        mainframe.setLayout(null);

        //toolbar
        Container contentPane = mainframe.getContentPane();
        JToolBar tb = new JToolBar();
        JButton tbStart = new JButton("Start");
        JButton tbReset = new JButton("Reset");
        JButton tbExit = new JButton("Exit");

        tb.add(tbStart); tb.add(tbReset); tb.add(tbExit);
        tb.setFloatable(false);
        tb.setBounds(0,0,400,20);
        mainframe.add(tb);

        // mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // board
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


        // dice rolls
        JPanel dicePanel = new JPanel();
        JTextField d1 = new JTextField("D1");
        JTextField d2 = new JTextField("D2");
        JTextField sum = new JTextField("sum:     ");
        sum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JButton roll = new JButton("roll");

        d1.setEditable(false); d2.setEditable(false); sum.setEditable(false);
        dicePanel.add(d1); dicePanel.add(d2); dicePanel.add(sum);
        dicePanel.add(roll);

        mainPanel.add(dicePanel);

        // p1/p2 turn
        JPanel turnPanel = new JPanel();
        JTextField turnInfo = new JTextField("Player turn:     ");
        turnInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        turnInfo.setBounds(0,0,20,30);
        turnInfo.setEditable(false);
        turnPanel.add(turnInfo);
        mainPanel.add(turnPanel);

        // placement of mainPanel
        mainPanel.setBounds(0,20,wSize[0],wSize[0]-20);
        mainframe.add(mainPanel);

        // actionlisteners
        tbExit.addActionListener(e -> {
            mainframe.dispose();
        });

    }
}
