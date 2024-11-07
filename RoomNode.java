public class RoomNode {

    private int door;
    private final int x;
    private final int y;
    private final boolean exit;
    private Player[] occupants;
    Door doorUp = null;
    Door doorRight = null;
    Door doorLeft = null;
    Door doorDown = null;
    Maze parent;
    public RoomNode(){
        int door = 0;
        x = 0;      //Room coords
        y = 0;      //Top room will be y = 0, x = width/2
        exit = false; //if exit is true, this is the destination room, the exit of the maze
        occupants = new Player[4];

    }
    public RoomNode(Maze parent, int door, int x, int y, boolean exit){
        this.parent = parent;
        this.door = door;
        this.x = x;
        this.y = y;
        this.exit = exit;
    }
    public boolean addPlayer(Player player){
        for(int i = 0; i < 4; i++){
            if(occupants[i] == null){
                occupants[i] = player;
                return true;
            }
        }
        return false;
    }
    ///Creates door objects based on the room's 'door' int value.
    /// Door has a value of 0 - 15.
    ///      8 (N)
    /// (W)4   2 (E)
    ///      1 (S)
    /// Each value means there is a door on that cardinal side.
    /// This method operates by first testing for a south door, then
    /// north, then west, then east. Each door created subtracts its value
    /// from the doorTemp integer (doorTemp is local to this method)
    /// @author Gus W
    public void addDoors(){


        if(door > 0){
            int doorTemp = door;
            if((doorTemp - 1) % 2 == 0){ //if door - 1 is even, then it has a 1 in there
                doorDown = new Door(2, parent.getNode(this.x, this.y + 1) );
                doorTemp -= 1;
            }
            if(doorTemp >= 8) { //now from the largest, descending
                doorUp = new Door(0, parent.getNode(this.x, this.y - 1));
                doorTemp -= 8;
            }
            if(doorTemp >= 4){
                doorRight = new Door(1, parent.getNode(this.x +1, this.y));
                doorTemp -= 4;
            }
            if(doorTemp >= 2){
                doorLeft = new Door(3, parent.getNode(this.x - 1, this.y));
                doorTemp -= 2;
            }
            if(doorTemp > 0){
                System.out.println("Error adding doors, value was over 15? Something went wrong. Check addDoors() method.");
                //doorTemp should be at zero, no matter what now. If it isn't something went wrong
            }
        }
        return;

    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDoor(){
        return door;
    }

    @Override
    public int hashCode(){
        final int prime = 31; //prime number used to avoid collisions
        int result = 1;
        result = result * prime + (exit ? 0 : 1);
        result = result * prime + x;
        result = result * prime + y;
        result = result * prime + door;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomNode roomNode = (RoomNode) o;
        return door == roomNode.door && x == roomNode.x && y == roomNode.y && exit == roomNode.exit;
    }
}
