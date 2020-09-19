package GameCenter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SnakesLadders {
    public int boardButtonSize = 10; // board button
    public int[] wSize = {400,400};
    public int[][] board = {{0,0,0,0,0,-3}, // -3 finish, -4 start
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {-4,0,0,0,0,0}};

    final public diceHandler dh = new diceHandler();
    public SnakesLadders(){
        JFrame mainframe = new JFrame("Snakes&Ladders");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // dice rolls
        int dOffset = 25;
        int diceSize = 30;
        JTextField d1 = new JTextField("D1");
        JTextField d2 = new JTextField("D2");
        JTextField sum = new JTextField("sum: ");

        d1.setBounds(wSize[0]/4-dOffset,10,diceSize, diceSize);
        d2.setBounds(wSize[0]/2-dOffset,10,diceSize, diceSize);
        sum.setBounds(wSize[0]/2+dOffset,10,60, 30);
        d1.setEditable(false); d2.setEditable(false); sum.setEditable(false);
        mainframe.add(d1); mainframe.add(d2); mainframe.add(sum);

        dh.roll(d1);

        mainframe.setSize(400, 400);   // set window size
        mainframe.setLocationRelativeTo(null); // middle of screen
        mainframe.setLayout(null); // no layout manager
        mainframe.setResizable(false); // not resizable
        mainframe.setVisible(true); // is visable



    }
}
