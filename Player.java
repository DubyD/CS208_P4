import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    RoomNode room;
    public Player(int x, int y) {
      super(x, y);
    }
    public void update(){

    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(getPlayerImg(), x, y, null);
    }
    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon("/Users/alirezarohani-rankouhi/Stick_Image/tick-man-smiley.png");
        return  ic.getImage();
    }
    public void keyPressed(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){

    }
    public void setRoom(RoomNode room){

    }
    public RoomNode getRoom(){
        return room;
    }
}
