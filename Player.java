import javax.swing.*;
import java.awt.*;

public class Player {
    public RoomNode room;
    private Color color;

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
        final int prime = 31;
        int result = 1;
        result = prime * result + (room == null ? 0 : room.hashCode()); // Player's room as a factor
        result = prime * result + color.hashCode(); // Color of the player
        return result;
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

