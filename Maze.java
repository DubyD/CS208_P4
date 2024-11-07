public class Maze {
    private RoomNode[][] rooms;
    private int width;
    private int height;
    private int numPlayers;
    private RoomNode start;


    public Maze(){
        this.width = 0;
        this.height = 0;
        this.numPlayers = 0;
    }
    
    public Maze(int width, int height, int numPlayers){
        this.width = width;
        this.height = height;
        this.numPlayers = numPlayers;
        //generateMaze();
    }



    public Maze(int numOfPlayers){
        this.width = 5;
        this.height = 5;
        this.numPlayers = numOfPlayers;
        //rooms is set in generateMaze()
        //generateMaze();
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



}
