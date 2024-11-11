import javax.swing.*;
import java.awt.*;

public class Player {
    public RoomNode room;
    private Color color;
    public Player(int x, int y) {
        color = Color.red;
    }


    public void setColor(Color c){
        this.color = c;
    }
    public Color getColor(){
        return color;
    }
    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon("/Users/alirezarohani-rankouhi/Stick_Image/tick-man-smiley.png");
        return  ic.getImage();
    }


    public void setRoom(RoomNode room){
        this.room = room;
    }
    public RoomNode getRoom(){
        return room;
    }
}

