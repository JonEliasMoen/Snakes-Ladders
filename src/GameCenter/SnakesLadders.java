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
        mainBoard.createBoard(mainPanel);

        // dice rolls
        JPanel dicePanel = new JPanel();
        JTextField d1 = new JTextField("Dice1");
        JTextField d2 = new JTextField("Dice2");
        JTextField sum = new JTextField("Sum:     ");
        sum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JButton roll = new JButton("roll");

        d1.setEditable(false); d2.setEditable(false); sum.setEditable(false);
        dicePanel.add(d1); dicePanel.add(d2); dicePanel.add(sum);
        dicePanel.add(roll);

        mainPanel.add(dicePanel);

        // p1/p2 turn / info
        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.PAGE_AXIS));
        JTextField divInfo = new JTextField("");
        JTextField turnInfo = new JTextField("Player turn:     ");

        divInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        divInfo.setSize(20,30);
        divInfo.setEditable(false);

        turnInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
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
        roll.addActionListener(e->{
            divInfo.setText("");
            int[] dice = dh.roll(d1, d2, sum);
            if(dice[0] == dice[1]){
                divInfo.setText("Roll again!");
            }
        });

    }
}
