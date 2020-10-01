package GameCenter;
import javax.swing.*;
import java.util.ArrayList;

public class SnakesLadders {
    public int[] wSize = {600, 675}; // 400, 450

    public ArrayList<SnakePlayer> players = new ArrayList<>();



    final public diceHandler dh = new diceHandler();
    public SnakeBoard mainBoard = new SnakeBoard();
    int[] turnData = {0, 1}; // playerturn, maxplayers.
    boolean gameGoing = false;
    public JTextField divInfo;
    public boolean canRoll = true;
    int[] specialMove = new int[5];

    public SnakesLadders() {
        addPlayers();

        // mainframe
        JFrame mainframe = new JFrame("Snakes&Ladders");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainframe.setSize(wSize[0], wSize[1]);   // set window size
        mainframe.setLocationRelativeTo(null); // middle of screen
        mainframe.setLayout(null); // no layout manager
        mainframe.setResizable(false); // not resizable
        mainframe.setVisible(true); // is visable
        mainframe.setLayout(null);

        //MenuBar
        JMenuBar mb = new JMenuBar();

        //MenuBar - file
        JMenu File = new JMenu("File");

        JMenuItem mbStart = new JMenuItem("Start");
        JMenuItem mbReset = new JMenuItem("Reset");
        JMenuItem mbExit = new JMenuItem("Exit");
        JMenuItem addPlayer = new JMenuItem("Add Player");

        File.add(mbStart); File.add(addPlayer);
        File.add(mbReset); File.add(mbExit);

        //MenuBar - file
        JMenu Help = new JMenu("Help");
        JMenuItem mbHelp = new JMenuItem("How to Play");
        Help.add(mbHelp);

        //MenuBar - About
        JMenu About = new JMenu("About");
        JTextArea version = new JTextArea("Version 1.0.0");
        JTextArea author = new JTextArea("Jon Elias Moen");
        About.add(version); About.add(author);


        mb.add(File); mb.add(Help); mb.add(About);
        mb.setBounds(0,0,400,20);
        mainframe.add(mb);

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
        divInfo = new JTextField("");
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
        mbExit.addActionListener(e -> mainframe.dispose()); // close window
        roll.addActionListener(e-> {
            if(canRoll) { // can roll
                int[] s = dh.roll(d1, d2, sum, divInfo); // roll dice
                takeTurn(s, divInfo, turnInfo); // take turn for this player
            }else{
                divInfo.setText("player must click ladder/snake before new roll");
            }
        });
        addPlayer.addActionListener(e->{
            if(!gameGoing) { // game is not started
                players.add(new SnakePlayer()); // add player
                turnData[1] += 1; // add max index
                divInfo.setText("Player " + (turnData[1] + 1) + " added!"); // update info
            }else{
                divInfo.setText("Cant add player during game. reset  the game"); // tried to add player during game
            }
        });
        mbStart.addActionListener(e->{
            resetGame(); // make sure game is resetted
            gameGoing = true; // start game
            divInfo.setText("Game started, p1 click roll to begin");

        });
        mbReset.addActionListener(e->{
            resetGame();
        });
        mbHelp.addActionListener(e->{
            Help hp = new Help();
        });

    }
    void resetGame(){
        canRoll = true; gameGoing = false; // reset global variables
        turnData[0] = 0; // set turn to player 1
        specialMove = new int[5]; // reset special move
        for(int i = 0; i<players.size(); i++){ // loop all players
            SnakePlayer s = players.get(i); // get player i
            s.setPos(0,5); // set position to start
            mainBoard.move(s, i, divInfo); // move player
        }
        divInfo.setText("Game Resetted");
    }
    void addPlayers() { // Add players to the game.
        players.add(0, new SnakePlayer());
        players.add(1, new SnakePlayer());
    }

    public void takeTurn(int[] dice, JTextField divInfo, JTextField turnInfo) {
        // take a turn in the game.
        if (gameGoing) { // If the game is going
            SnakePlayer sp = players.get(turnData[0]); // get snakeplayer object

            if (!sp.moveHandler(dice[2], divInfo)) { // Is a legal move.
                specialMove = mainBoard.move(sp, turnData[0], divInfo); // do the move

                if (sp.x == 0 && sp.y == 0) { // player has won the game
                    divInfo.setText("Player " + (turnData[0] + 1) + " wins");
                    gameGoing = false;
                }

                if(specialMove[0] != -1){ // SNAKE/LADDER ACTION
                    canRoll = false; // blocks other player of rolling
                    // add actionListner for the button.
                    mainBoard.Bboard[specialMove[4]][specialMove[3]].addActionListener(e->{
                        if(specialMove[0] != -1) {  // just hit the ladder or snake
                            canRoll = true; // set the other players can roll.
                            SnakePlayer sp2 = players.get(specialMove[0]); // get the player
                            sp2.setPos(specialMove[1], specialMove[2]); // move the player.
                            players.set(specialMove[0], sp2); // be 100% sure its updated
                            mainBoard.move(sp2, specialMove[0], divInfo); // move the player down the ladder

                            // remove this actionlistener. cant go up ladder after again later.
                            mainBoard.Bboard[specialMove[4]][specialMove[3]].removeActionListener(mainBoard.Bboard[specialMove[4]][specialMove[3]].getActionListeners()[0]);
                            divInfo.setText(""); // reset divinfo
                        }
                    });
                }
            }
            if (dice[0] != dice[1]) { // not roll again
                turnData[0] += 1; //  next players turn
                if (turnData[0] > turnData[1]) { // goes over playercount
                    turnData[0] = 0; // reset
                }
                turnInfo.setText("Player turn:     P" + (turnData[0] + 1));
            }
        } else {
            divInfo.setText("Game is not running");
        }
    }
}
