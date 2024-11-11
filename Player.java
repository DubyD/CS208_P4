import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Player {
    RoomNode room;
    int velX = 0, velY = 0;
    int speed = 2;
    public Player(int x, int y) {
        velX = x;
        velY = y;
        speed =0;
    }

    /**public void update(List<Wall> walls, List<Gate> gates) {
        int newX = x + velX;
        int newY = y + velY;

        // Create a rectangle for the player's new position
        Rectangle playerBounds = new Rectangle(newX, newY, getPlayerImg().getWidth(null), getPlayerImg().getHeight(null));

        // Check for collision with walls
        for (Wall wall : walls) {
            if (wall.getBounds().intersects(playerBounds)) {
                return; // Collision detected with wall, do not move
            }
        }

        // Update position if no wall collision
        x = newX;
        y = newY;

        // Check for collision with gates
        for (Gate gate : gates) {
            if (gate.getBounds().intersects(playerBounds)) {
                // Stop player movement and change to Game Over Scene
                JOptionPane.showMessageDialog(null, "You have reached the Gate! Game has Ended!");
                System.exit(0); // Close the application
            }
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(getPlayerImg(), x, y, null);
    }
    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon("/Users/alirezarohani-rankouhi/Stick_Image/tick-man-smiley.png");
        return  ic.getImage();
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
            velY = -speed;
        }else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
            velY = speed;
        }else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            velX = -speed;
        }else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            velX = speed;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
            velY = 0;
        }else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
            velY = 0;
        }else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            velX = 0;
        }else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            velX = 0;
        }
    }*/
    public void setRoom(RoomNode room){

    }
    public RoomNode getRoom(){
        return room;
    }
}

