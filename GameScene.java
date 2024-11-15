/**
 * GameScene.java
 * The game scene/screen that contains the maze and gameplay.
 * Operates as a sort of controller class. Organizes the maze GUI and draws extraneous elements
 *
 * @author Gus Warmington
 */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.*;


public class GameScene extends JPanel{

    JButton exitButton = new JButton("Exit");; //persistent exit button
    Maze maze; //instance of maze object, instantiated in constructor
    RoomNode[][] grid; //maze grid, received from maze object

    private final int HEIGHT = 5; //Number of rows
    private final int WIDTH = 5; //number of columns
    private static int turnsTaken;
    private boolean finishedGame;
    ///default constructor
    public GameScene() {
        //default only 1 player
        maze = null;
        grid = null;
        finishedGame = false;
        turnsTaken = 0;
        this.drawMaze();
    }

    ///@param numPlayers the number of players in the game
    public GameScene(int numPlayers) {
        maze = new Maze(WIDTH, HEIGHT, numPlayers);
        grid = maze.getMaze();
        finishedGame = false;
        turnsTaken = 0;
        this.drawMaze();
    }

    ///Lays out the grid of RoomNodes, also adds in two JLabels for flavor text, and the exit button
    private void drawMaze(){
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
                    if(x != WIDTH -1){
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
    public boolean getFinished(){
        return finishedGame;
    }
    public Maze getMaze() {
        return maze;
    }

    /**
     * The main component to cycle through each turn
     */
    public void takeTurn(){

        for(Player player : maze.getPlayers()){
            setFocusable(true);
            requestFocusInWindow();

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        maze.travel(player, 0, -1);
                        System.out.println("Moved Up");//to debug, same for all other keys

                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        maze.travel(player, 0, 1);

                        System.out.println("Moved Down");
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        maze.travel(player, -1, 0);

                        System.out.println("Moved Left");
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        maze.travel(player, 1, 0);

                        System.out.println("Moved Right");
                    }
                }
            });
            drawMaze();
        }
        for(Player player : maze.getPlayers()){

        }


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

