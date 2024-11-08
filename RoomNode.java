public class RoomNode {

    private Door[] doors;
    private Player[] occupants;
    private Maze parent;
    private RoomNode nextNode;

    private int door;
    private final int x;
    private final int y;
    private final boolean exit;


    private boolean trap;

    public RoomNode(){
        int door = 0;
        x = 0;      //Room coords
        y = 0;      //Top room will be y = 0, x = width/2
        exit = false; //if exit is true, this is the destination room, the exit of the maze
        occupants = new Player[4];
        doors = new Door[4];
    }
    public RoomNode(Maze parent, int door, int x, int y, boolean exit){
        this.parent = parent;
        this.door = door;
        this.x = x;
        this.y = y;
        this.exit = exit;
        this.trap = false;
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
        return;

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
    public boolean isExit(){
        return exit;
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
    public void setPathDoors(RoomNode other){
        int tempX = this.x - other.getX();
        int tempY = this.y - other.getY();
        if(tempX < 0){

        }
        if(tempX > 0){

        }
        if(tempY < 0){

        }
        if(tempY > 0){

        }
    }
    public char[] getDoorDirections(){
        int temp = door;
        char[] reply = new char[0];
        while(temp > 0){
            if((temp - 8) > 0){
                temp = temp - 8;
                char[] tempList = new char[reply.length + 1];

                System.arraycopy(reply, 0, temp, 0, reply.length);
                tempList[tempList.length - 1] = 'N';
                reply = tempList;
            }
            if((temp - 4) > 0){
                temp = temp - 4;
                char[] tempList = new char[reply.length + 1];
                System.arraycopy(reply, 0, temp, 0, reply.length);
                tempList[tempList.length - 1] = 'W';
                reply = tempList;
            }
            if((temp - 2) > 0){
                temp = temp - 2;
                char[] tempList = new char[reply.length + 1];
                System.arraycopy(reply, 0, temp, 0, reply.length);
                tempList[tempList.length - 1] = 'E';
                reply = tempList;
            }
            if((temp - 1) > 0){
                temp = temp - 1;
                char[] tempList = new char[reply.length + 1];
                System.arraycopy(reply, 0, temp, 0, reply.length);
                tempList[tempList.length - 1] = 'S';
                reply = tempList;
            }
        }
        return reply;
    }

    /**
     * Setters
     */
    public void setDoor(int door){
        this.door = door;
    }
    public void setExit(){
        this.exit = true;
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


/**
 * Code that is boring simple logic
 * with the same results as
 * getDoorDirection()
 * 
 if(door == 0){
 return null;
 }
 if(door == 1){
 return new char[]{'S'};
 }
 if(door == 2){
 return new char[]{'E'};
 }
 if(door == 3){
 return new char[]{'E','S'};
 }
 if(door == 4){
 return new char[]{'W'};
 }
 if(door == 5){
 return new char[]{'S','W'};
 }
 if(door == 6){
 return new char[]{'E','W'};
 }
 if(door == 7){
 return new char[]{'E','S','W'};
 }
 if(door == 8){
 return new char[]{'N'};
 }
 if(door == 9){
 return new char[]{'N','S'};
 }
 if(door == 10){
 return new char[]{'N','E'};
 }
 if(door == 11){
 return new char[]{'N','E','S'};
 }
 if(door == 12){
 return new char[]{'N','W'};
 }
 if(door == 13){
 return new char[]{'N','W','S'};
 }
 if(door == 14){
 return new char[]{'N','E','W'};
 }
 if(door == 15){
 return new char[]{'N','E','S','W'};
 }
 return null;
 */
