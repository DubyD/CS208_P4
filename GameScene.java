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
    private void drawMaze(){
        this.setLayout(new GridLayout(HEIGHT+2,WIDTH, 5, 5));//see comment below for +2 on height
        for(int i = 0; i < WIDTH; i++){
            this.add(new JLabel());//filler jlabels for vertical padding
        }
        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++) {
                    //JLabel node = new JLabel("x: " + x + ", y: " + y);
                    RoomNode node = maze.getNode(x, y);
                    this.add(node);
            }
        }
//        for(int i = 0; i < maze.getMaze()[0].length; i++){
//            this.add(new Box.Filler(new Dimension(20,20),new Dimension(20,20),new Dimension(20,20)));
//        }

        this.revalidate();
        this.repaint();
    }
    public JButton getExitButton() {
        return exitButton;
    }
//    @Override
//    protected void paintComponent(Graphics g){
//        int startX = (int) (cellWidth * (1/WIDTH));
//        int startY = (int) (cellHeight * (1/HEIGHT));
//        Graphics2D g2d = (Graphics2D) g;
//        Dimension d = getSize();
//        g2d.setColor(Color.GRAY);
//        g2d.fillRect(0,0,d.width, d.height);
//
//        for(int y = 0; y < grid.length; y++){
//            for(int x = 0; x < grid[0].length; x++){
//                g2d.setColor(Color.BLUE);
//                g2d.fillRect(startX, startY, cellWidth, cellHeight);
//                startX += (int) (cellWidth * 1.5);
//            }
//            startX = (int) (cellWidth * .5);
//            startY += (int) (cellHeight * 1.5);
//        }
//    }


    /*
     * Gus when you get to the doors, door directions are:
     * 0 == up, 1 == right, 2 == down, 3 == left.
     */

}

