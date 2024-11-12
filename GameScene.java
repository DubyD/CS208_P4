import java.awt.*;

import javax.swing.*;


public class GameScene extends JPanel{

    Player[] players;
    JButton exitButton;
    Maze maze;
    RoomNode[][] grid;
    private final int HEIGHT = 5;
    private final int WIDTH = 5;
    public GameScene() {
        players = null;
        exitButton = null;
        maze = new Maze();
        grid = maze.getMaze();
    }

    public GameScene(int numPlayers) {
        players = new Player[numPlayers];
        exitButton = new JButton("Exit");
        maze = new Maze(WIDTH, HEIGHT, numPlayers);
        grid = maze.getMaze();

        this.drawMaze();
        //this.add(exitButton);
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
    public JButton getExitButton() {
        return exitButton;
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

}

