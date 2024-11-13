import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Player {
    private RoomNode room;
    private Color color;
    private int x;
    private int y;

    private static final Color[] predefinedColors = {
        new Color(255, 0, 0),   // Red
        new Color(0, 255, 0),   // Green
        new Color(0, 0, 255),   // Blue
        new Color(255, 255, 0)  // Yellow
    };
    private static int playerCount = 0; // Keep track of the number of players

    // Default constructor assigns a unique color based on player count
    public Player() {
        color = predefinedColors[playerCount % predefinedColors.length];
        playerCount++; // Increment player count for the next player
    }

    // Constructor with coordinates (also assigns unique color)
    public Player(int x, int y) {
        color = predefinedColors[playerCount % predefinedColors.length];
        playerCount++; // Increment player count for the next player
    }

    public void removePlayer(){
        playerCount--;
    }
    @Override
    public int hashCode() {
        final int prime = 31; //prime number used to avoid collisions
        int result = 1;
        result = result * prime + x;
        result = result * prime + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        return Objects.equals(getRoom(), other.getRoom());
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
        this.x = room.getXIndex();
        this.y = room.getYIndex();
    }
    public RoomNode getRoom(){
        return room;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}

