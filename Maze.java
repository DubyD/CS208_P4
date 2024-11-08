import java.util.Random;

public class Maze {
    private RoomNode[][] rooms;
    private int width;
    private int height;
    private int numPlayers;
    private RoomNode start;
    private SingleLinkedList path;


    public Maze(){
        this.width = 0;
        this.height = 0;
        this.numPlayers = 0;
        this.start = null;
        this.path = null;
    }

    // If we get a working version we will create dynamic sizing
    public Maze(int width, int height, int numPlayers){
        this.width = width;
        this.height = height;
        this.numPlayers = numPlayers;
        //generateMaze();
        this.start = rooms[3][0];
        this.path = new SingleLinkedList();

    }

    public Maze(int numOfPlayers){
        this.width = 5;
        this.height = 5;
        this.numPlayers = numOfPlayers;
        //rooms is set in generateMaze()
        //generateMaze();
        this.path = genPath();
        this.start = this.path.getHead();
    }

//----------WORKING ZONE NOT FINISHEDs-----------------------------------------------
    private SingleLinkedList genPath(){
        int i;
        int j = 0;
        if(width % 2 == 1){
            i = (width + 1) / 2;
        }else{
            i = width / 2;
        }

        int nextRoom = 0;
        int lastRoom = 0;
        Random rand = new Random();
        SingleLinkedList temp = new SingleLinkedList();
        boolean looping = true;
        while(looping){
            temp.insertAtTail(rooms[i][j]);
            lastRoom = nextRoom;
            nextRoom = 1 + rand.nextInt(4);
            if(nextRoom == 1){

            }
            if(nextRoom == 2){

            }
            if(nextRoom == 3){

            }
            if(nextRoom == 4){

            }

            if(){
                looping = false;
            }
        }
    }

    private void generateMaze(){

    }

    ///@param p The player that wishes to move
     /// @param direction the direction the player wishes to move
     /// 0 = up, 1 = right, 2 = down, 3 = left
     /// @return true if successful, false if p cannot move to desired destination
    private boolean travel(Player p, int direction) {
        Door[] doors = p.getRoom().getDoors();
        if(doors[direction] != null){
             p.setRoom(doors[direction].getDestination());
             return true;
        }
        else{
            return false;
        }

    }

    public RoomNode getNode(int x, int y){
            if(x >= 0 && y >= 0) {//avoid index out of bounds
                return rooms[y][x];
            }
            return null;

    }

    public RoomNode getStart(){
        return start;
    }

    public RoomNode getFinish(){
        return path.getTail();
    }



}
