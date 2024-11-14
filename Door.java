/**
 * Door.java
 * @author Gus Warmington
 * @author
 * Door object, to be placed on RoomNodes.
 * Direction: 0 - UP 1 - RIGHT 2 - DOWN 3 - LEFT
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Door extends JComponent{

    private final int direction;
    private final RoomNode destination;
    private boolean exit;

    ///default constructor, should probably not be used in this state
    public Door() {
        this.direction = 0;
        this.destination = new RoomNode();
        this.exit = false;
    }
    ///@param direction the side of the room for the door to be on
     /// @param destination the roomnode that this door leads to
    public Door(int direction, RoomNode destination){
        this.direction = direction; //which side of the room it is on
                                    // 0 means up, 1 means right, 2 means down, 3 means left
        this.destination = destination; // where the door leads to
        this.exit = false;
    }
    ///@return the destination roomnode
    public RoomNode getDestination() {
        return destination;
    }
    ///@return the direction int value
    public int getDirection() { return direction;}

    ///sets this door as the exit door, should only be one per maze
    public void setExit() { exit = true;}

    ///Painting on top of the RoomNode it is positioned on. Should be fine when resizing as well
    /// @author Gus Warmington
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        switch(this.direction){
            case 0:
                g2d.fillRect(3*(getWidth()/7), 0, ((getWidth()/7)), (getHeight()/10));
                break;
            case 1:
                g2d.fillRect(this.getWidth()-((getWidth()/10)), (2*(getHeight()/5)), ((getWidth()/10)), (getWidth()/5));
                break;
            case 2:
                g2d.fillRect((3*(getWidth()/7)), this.getHeight()-((getHeight()/10)), ((getWidth()/5)), (getHeight()/10));
                break;
            case 3:
                g2d.fillRect(0, (2*(getHeight()/5)), ((getWidth()/10)), (getWidth()/5));
                break;
        }
    }
    ///Equals method
    /// @param o the object to compare to this
     /// returns true if direction, exit, and destination match
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Door door = (Door) o;
        return getDirection() == door.getDirection() && exit == door.exit && this.getDestination().equals(door.getDestination());
    }
    @Override
    public String toString() {
        return "This is a portal that connect rooms. Direction: " + direction + ", Destination: " + destination.toString()
                + ". Is this door an exit? -> " + exit;
    }
}
