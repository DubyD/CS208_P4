/**
 * GameScene.java
 * The game scene/screen that contains the maze and gameplay.
 * Operates as a sort of controller class. Organizes the maze GUI and draws extraneous elements
 *
 * @author Gus Warmington
 * @author Will Duby
 * @author Sukhdeep Singh
 */

import javafx.scene.Scene;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.*;


public class GameScene extends JPanel{

    private JButton exitButton = new JButton("Exit");; //persistent exit button
    private Maze maze; //instance of maze object, instantiated in constructor
    private RoomNode[][] grid; //maze grid, received from maze object
    private SceneSwitcher sceneController;

    private final int HEIGHT = 5; //Number of rows
    private final int WIDTH = 5; //number of columns
    private static int turnsTaken = 0;
    private  int playerIndex = 0;
    private static boolean moveMade = false;
    private boolean finished;
    ///default constructor
    public GameScene() {
        //default only 1 player
        maze = null;
        grid = null;

        this.drawMaze();
    }

    ///@param numPlayers the number of players in the game
    public GameScene(int numPlayers, SceneSwitcher parent) {
        maze = new Maze(WIDTH, HEIGHT, numPlayers);
        grid = maze.getMaze();
        finished = false;
        this.drawMaze();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("Key pressed: " + e.getKeyCode());
                if (!moveMade) {
                    if(getPlayer(playerIndex) != null){
                        handleKeyPress(e, getPlayer(playerIndex));
                    }else{
                            //This ensures that the playerIndex is incremented for valid players
                        while(getPlayer(playerIndex) == null){
                            playerIndex = (playerIndex + 1) % maze.getPlayers().length;
                        }
                    }

                }
            }
        });
        sceneController = parent;
    }

    private Player getPlayer(int index) {
        Player[] players = maze.getPlayers();
        return players[index];
    }

    ///Lays out the grid of RoomNodes, also adds in two JLabels for flavor text, and the exit button
    private void drawMaze(){
        this.removeAll();
        this.revalidate();
        JLabel buttonContainer = new JLabel(); //blank jlabel to hold a smaller button
        buttonContainer.setLayout(new FlowLayout(FlowLayout.RIGHT,0, 48));
        this.add(new JLabel("Escape the house..."));
        this.setLayout(new GridLayout(HEIGHT+2,WIDTH, 5, 5));//see comment below for +2 on height
        for(int i = 0; i < WIDTH - 2; i++){
            this.add(new JLabel());//filler jlabels for vertical padding
        }
        InstructionsLabel narrator = new InstructionsLabel();
        this.add(narrator);
        for(int y = 0; y <= HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++) {
                if(y != HEIGHT) {
                    //JLabel node = new JLabel("x: " + x + ", y: " + y);
                    RoomNode node = maze.getNode(x, y);
                    this.add(node);
                }
                else{
                    if(x != WIDTH -1 && x != 0){
                        this.add(new JLabel());
                    }
                    else{
                        buttonContainer.add(exitButton);
                        this.add(buttonContainer);
                    }

                }
            }
        }
        this.revalidate();
        this.repaint();
    }
    ///@return the exitButton, for use in SceneSwitcher class
    public JButton getExitButton() {
        return exitButton;
    }
    public Maze getMaze() {
        return maze;
    }

    /**
     * The main component to cycle through each turn
     */

    private void handleKeyPress(KeyEvent e, Player player) {
        boolean validMove = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                validMove = maze.travel(player,player.getX() + 0,player.getY() - 1);
                break;
            case KeyEvent.VK_S:
                validMove = maze.travel(player,player.getX() + 0,player.getY() + 1);
                break;
            case KeyEvent.VK_A:
                validMove = maze.travel(player,player.getX() - 1,player.getY() + 0);
                break;
            case KeyEvent.VK_D:
                validMove = maze.travel(player,player.getX() + 1,player.getY() + 0);
                break;
        }

        if (validMove) {
            String option = "Player["+ (playerIndex + 1) + "] Moved " + getDirection(e.getKeyCode());
            JOptionPane.showMessageDialog(GameScene.this, option);
            moveMade = true; // Mark the move as made to allow loop to continue
            playerIndex = (playerIndex + 1) % maze.getPlayers().length;
            drawMaze();
        } else {
            //System.out.println();
            String option = "Player["+ (playerIndex + 1) + "] tried to move " + getDirection(e.getKeyCode()) + "\n"+
                            "This is an Invalid space, try a different direction.";
            JOptionPane.showMessageDialog(GameScene.this, option);
        }
    }

    private String getDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                return "W";
            case KeyEvent.VK_S:
                return "S";
            case KeyEvent.VK_A:
                return "A";
            case KeyEvent.VK_D:
                return "D";
            default:
                return "";
        }
    }

    public void takeTurn() {
        SwingWorker<Void, Void> gameWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                while (!finished) {
                    moveMade = false;
                    while (!moveMade) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    drawMaze();

                    if (playerIndex == 0) {
                        turnsTaken++;
                        maze.trapCheck();

                        // Check if the game is finished
                        if (!maze.getEndNode().isEmpty()|| !maze.hasPlayers()) {
                            finished = true;

                        }
                    }


                }
                return null;
            }

            @Override
            protected void done() {
                if(finished) {
                    sceneController.finishScreen(maze.getEndNode());
                }
            }
        };

        // Start the game loop in the background
        gameWorker.execute();
    }



    ///Paints house background and roof
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.darkGray);
        g2d.fillRect(0,(int)(maze.getNode(0,0).getHeight()*1.05), this.getWidth(),(int)(maze.getNode(0,0).getHeight()*(HEIGHT+.5)));
        g2d.setColor(new Color(94, 38, 0)); //Brown;
        g2d.fillPolygon(new int[]{0,getWidth()/2, getWidth()}, new int[] {(int)(maze.getNode(0,0).getHeight()*1.05), (int) (getHeight()*.05), (int)(maze.getNode(0,0).getHeight()*1.05)}, 3);

    }


    /**
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameScene gameScene = (GameScene) o;
        return Objects.equals(getExitButton(), gameScene.getExitButton()) && Objects.equals(maze, gameScene.maze) && Objects.deepEquals(grid, gameScene.grid);
    }

    @Override
    public String toString() {
        return "The main game screen. Current game session has " + maze.numPlayers() + "players, and the maze is " + HEIGHT +
                " rooms tall and " + WIDTH + " rooms wide.";
    }

}

