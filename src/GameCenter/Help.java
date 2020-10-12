package GameCenter;

import javax.swing.*;

public class Help {
    String helpText = "This is a multiplayer game. \nYou can add more players under file - add players. \nAdd as many players as you want then click start to begin. \nEach player clicks roll, and the player piece is moved according to the sum of the two dice. \nWhen a player lands on a snake the player must go down it, the same with ladders. \nYou must also click the player icon when this happens. \nthe game will alert you. \n If the two dice are equal player rolls again. \nThe first to the finish box wins";

    public Help() {
        JFrame mainframe = new JFrame("Snakes&Ladders");

        mainframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainframe.setSize(500, 500);   // set window size
        mainframe.setLocationRelativeTo(null); // middle of screen
        mainframe.setLayout(null); // no layout manager
        mainframe.setResizable(false); // not resizable
        mainframe.setVisible(true); // is visable
        mainframe.setLayout(null);

        JTextArea hpText = new JTextArea(helpText);
        hpText.setOpaque(false);
        hpText.setFont(hpText.getFont().deriveFont(12f));
        hpText.setBounds(0, 20, 500, 500);
        hpText.setEditable(false);
        mainframe.add(hpText);
    }
}
