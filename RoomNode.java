/**
 * @author Gus Warmington
 * A room object, extending a blank JLabel for ease of use as a JComponent.
 *
 */


import javax.swing.*;
import java.awt.*;

public class RoomNode extends JLabel{
    private final Door[] doors;
    private Player[] occupants; //Players currently in room
    //Used if on the path
    private RoomNode nextNode; //For use in pathGen

    private final int door;
    private final int x; //Notes the room's position in the maze grid
    private final int y;
    private boolean trap;
    private boolean trapRevealed;//For use in graphical representation, changes color when entered
    private boolean isExit;

    ///@param x the column this is relative to the maze grid
     /// @param y the row this is in relative to the maze grid
    public RoomNode(int x, int y){
        this.door = 0;
        this.x = x;
        this.y = y;
        this.trap = false;
        this.occupants = new Player[4];
        this.doors = new Door[4];
        this.setOpaque(true);
        this.nextNode = null;
        this.isExit = true;
    }
    ///default constructor
    public RoomNode(){
        this(-1,-1);
    }

    public boolean leavingRoom(Player player){
        for(int i = 0; i < occupants.length; i++){
            if(occupants[i] == player){
                occupants[i] = null;
                return true;
            }
        }
        return false;
    }

    ///@param player the player to be searched for
     /// @return true if the room contains the specific player
    public boolean hasPlayer(Player player){
        for(Player p : occupants){
            if(p.equals(player)){
                return true;
            }
        }
        return false;
    }
    public boolean isEmpty(){
        for(int i = 0; i < 4; i++){
            if(occupants[i] != null){
                return false;
            }
        }
        return true;
    }
    ///@param player to be added to this room
     /// @return true on success
    public boolean addPlayer(Player player){
        for(int i = 0; i < 4; i++){
            if(occupants[i] == null){
                occupants[i] = player;
                return true;
            }
        }
        return false;
    }
    ///@return array of doors in this room
    public Door[] getDoors() {
        return doors;
    }
    ///@return the X index, or column number.
     /// note: getX is a JComponent function, and as such would conflict
    public int getXIndex() {
        return x;
    }
    ///@return the Y index, or row number.
     /// note: getY is a JComponent function, and as such would conflict
    public int getYIndex(){
        return y;
    }
    ///Unused? delete if so
    public int getDoorValue(){
        return door;
    }
    ///@return true if this room is a pitfall
    public boolean isTrap(){
        return trap;
    }
    ///@return true if there is a room that connects
    public boolean hasNext(){
        if(nextNode == null){
            return false;
        }
        return true;
    }
    ///@return the next RoomNode
    public RoomNode getNextNode(){
        return nextNode;
    }

    /**
     * Setters
     */
    public void setTrapRevealed(boolean trap){
        this.trapRevealed = trap;
    }

    public void setDoor(int direction, RoomNode neighbor) {
        if (direction >= 0 && direction < 4) { // Ensure direction is within bounds
            this.doors[direction] = new Door(direction, neighbor);
        }
    }
    ///@param exit the RoomNode to be set as the exit node
     /// Sets the southernmost door as the exit door
    public void setExit(RoomNode exit){
        setDoor( 2,exit);
        doors[2].setExit();
        isExit = true;
    }

    public void setNext(RoomNode nextRoom){
        this.nextNode = nextRoom;
    }
    public void setTrap(){
        this.trap = true;
    }


    @Override
    public int hashCode(){
        final int prime = 31; //prime number used to avoid collisions
        int result = 1;
        result = result * prime + x;
        result = result * prime + y;
        return result;
    }
    ///@return true if x and y index are equal
     /// @param o the object to compare to
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomNode roomNode = (RoomNode) o;
        return  this.getXIndex() == roomNode.getXIndex() &&
                this.getYIndex() == roomNode.getYIndex();
    }
    public String toString(){
        return "Room ("+ x + "," + y + ")";
    }
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.LIGHT_GRAY);
        if(trapRevealed){
            g.setColor(Color.BLACK); //Change color when trap is revealed
        }
        g2d.fillRect(5,5, this.getWidth()-10, this.getHeight()-10);
        for (int i = 0; i < occupants.length; i++) { //Paint players/occupants in the room
            Player occupant = occupants[i];
            if (occupant != null) {
                g2d.setColor(occupant.getColor());
                g2d.fillOval(((i) * (this.getWidth() / 5)) + (getWidth()/8), this.getHeight() / 2, this.getWidth() / 9, this.getWidth() / 9);
            }
        }
        g2d.setColor(new Color(94, 38, 0));//Brown
        for(Door door : doors){     //Paint doors in rooms
            if(door != null){
                switch(door.getDirection()){
                    //0 = up, 1 = right, 2 = down, 3 = left
                    case 0:
                        g2d.fillRect(3*(getWidth()/7), 0, ((getWidth()/5)), (getHeight()/10));
                        break;
                    case 1:
                        g2d.fillRect(this.getWidth()-((getWidth()/15)), ((getHeight()/3)), ((getWidth()/15)), (getHeight()/3));
                        break;
                    case 2:
                        g2d.fillRect((3*(getWidth()/7)), this.getHeight()-((getHeight()/10)), ((getWidth()/5)), (getHeight()/10));
                        break;
                    case 3:
                        g2d.fillRect(0, ((getHeight()/3)), ((getWidth()/15)), (getHeight()/3));
                        break;
                }
            }

        }

    }

}