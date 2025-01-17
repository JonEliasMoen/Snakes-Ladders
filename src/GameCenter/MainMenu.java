package GameCenter;

import javax.swing.*;

public class MainMenu {
    public MainMenu() {
        int size = 400;
        int buttonHeight = 30;
        int buttonWidth = 110;

        JFrame menu = new JFrame("Game Center");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea title = new JTextArea("Game Center");
        title.setOpaque(false);
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(140, 20, 400, 80);
        title.setEditable(false);

        JButton rollDice = new JButton("Roll a Dice");
        JButton maxClicks = new JButton("Max Clicks");
        JButton snakeLadder = new JButton("Snakes&Ladders");
        JButton exit = new JButton("Exit Game");

        rollDice.setBounds(150, 120, buttonWidth, buttonHeight);
        maxClicks.setBounds(150, 170, buttonWidth, buttonHeight);
        snakeLadder.setBounds(150, 220, buttonWidth, buttonHeight);
        exit.setBounds(280, 300, buttonWidth, buttonHeight);

        menu.add(title);
        menu.add(maxClicks);
        menu.add(rollDice);
        menu.add(snakeLadder);
        menu.add(exit);
        menu.setSize(size, size);
        menu.setLocationRelativeTo(null);
        menu.setLayout(null);
        menu.setVisible(true);
        menu.setResizable(false);

        rollDice.addActionListener(e -> new DiceRollGame());
        maxClicks.addActionListener(e -> new MaxClickGame());
        snakeLadder.addActionListener(e -> new SnakesLadders());
        exit.addActionListener(e -> menu.dispose());
    }

}
