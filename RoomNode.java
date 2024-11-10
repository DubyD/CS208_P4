public class RoomNode {

    private Door[] doors;
    private Player[] occupants;
    //Used if on the path
    private RoomNode nextNode;
    private int door;
    private final int x;
    private final int y;


    private boolean trap;

    public RoomNode(){
        door = 0;
        x = 0;      //Room coords
        y = 0;      //Top room will be y = 0, x = width/2
        occupants = new Player[4];
        doors = new Door[4];
        trap = false;
    }
    public RoomNode(int x, int y){
        this.door = 0;
        this.x = x;
        this.y = y;
        this.trap = false;
        occupants = new Player[4];
        doors = new Door[4];
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
    public Door[] getDoors() {
        return doors;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDoorValue(){
        return door;
    }
    public boolean isTrap(){
        return trap;
    }
    public boolean hasNext(){
        return nextNode != null;
    }
    public RoomNode getNextNode(){
        return nextNode;
    }
    /**
     * Setters
     */
    public void setDoor(int direction, RoomNode neighbor) {
        if (direction >= 0 && direction < 4) { // Ensure direction is within bounds
            this.doors[direction] = new Door(direction, neighbor);
        }
    }

    public void setExit(RoomNode exit){
        setDoor( 2,exit);

    }

    
    public void setNext(RoomNode nextRoom){
        this.nextNode = nextRoom;
    }
    public void setTrap(){
        this.trap = true;
    }


    /**
     * Gus wrote the hashcode not will
     */
    @Override
    public int hashCode(){
        final int prime = 31; //prime number used to avoid collisions
        int result = 1;
        //result = result * prime + (exit ? 0 : 1);
        result = result * prime + x;
        result = result * prime + y;
        //result = result * prime + door;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomNode roomNode = (RoomNode) o;
        return  x == roomNode.x &&
                y == roomNode.y;
    }
}


/**
 * Code that is boring simple logic
 * with the same results as
 * getDoorDirection()
 *
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
 doors[0] = null;
 doors[1] = null;
 doors[2] = null;
 doors[3] = null;

 if(door > 0){
 int doorTemp = door;
 if((doorTemp - 1) % 2 == 0){ //if door - 1 is even, then it has a 1 in there
 doors[2] = new Door(2, parent.getNode(this.x, this.y + 1) );
 doorTemp -= 1;
 }
 if(doorTemp >= 8) { //now from the largest, descending
 doors[0] = new Door(0, parent.getNode(this.x, this.y - 1));
 doorTemp -= 8;
 }
 if(doorTemp >= 4){
 doors[1] = new Door(1, parent.getNode(this.x +1, this.y));
 doorTemp -= 4;
 }
 if(doorTemp >= 2){
 doors[3] = new Door(3, parent.getNode(this.x - 1, this.y));
 doorTemp -= 2;
 }
 if(doorTemp > 0){
 System.out.println("Error adding doors, value was over 15? Something went wrong. Check addDoors() method.");
 //doorTemp should be at zero, no matter what now. If it isn't something went wrong
 }
 for(int i = 0; i < 4; i++){
 if(doors[i] != null){
 if(doors[i].getDestination() == null){
 doors[i] = null; //deleting doors that don't have a destination
 }
 }
 }
 }
 }

 * Adds doors between this room and the specified neighboring room based on their relative positions.
 * This method updates the door values in this room so that doors are created in the direction of the neighboring room.

 public void compareNextRoom(RoomNode other) {
 int tempX = other.getX() - this.x;
 int tempY = other.getY() - this.y;

 // Check each direction and update the door value accordingly
 if (tempX < 0) {
 // East direction (other is to the left)
 door += 2;
 } else if (tempX > 0) {
 // West direction (other is to the right)
 door += 4;
 }

 if (tempY < 0) {
 // South direction (other is below)
 door += 1;
 } else if (tempY > 0) {
 // North direction (other is above)
 door += 8;
 }
 }


 public String[] getDoorDirections(){
 int temp = door;
 String[] reply = new String[0];
 while(temp > 0){
 if((temp - 8) > 0){
 temp = temp - 8;
 String[] tempList = new String[reply.length + 1];

 System.arraycopy(reply, 0, temp, 0, reply.length);
 tempList[tempList.length - 1] = "up";
 reply = tempList;
 }
 if((temp - 4) > 0){
 temp = temp - 4;
 String[] tempList = new String[reply.length + 1];
 System.arraycopy(reply, 0, temp, 0, reply.length);
 tempList[tempList.length - 1] = "left";
 reply = tempList;
 }
 if((temp - 2) > 0){
 temp = temp - 2;
 String[] tempList = new String[reply.length + 1];
 System.arraycopy(reply, 0, temp, 0, reply.length);
 tempList[tempList.length - 1] = "right";
 reply = tempList;
 }
 if((temp - 1) > 0){
 temp = temp - 1;
 String[] tempList = new String[reply.length + 1];
 System.arraycopy(reply, 0, temp, 0, reply.length);
 tempList[tempList.length - 1] = "down";
 reply = tempList;
 }
 }
 return reply;
 }
 */
