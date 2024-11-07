public class Maze {
    private RoomNode[][] rooms;
    int width;
    int height;
    int numPlayers;
    RoomNode start;
    public Maze(int width, int height, int numPlayers){
        this.width = width;
        this.height = height;
        this.numPlayers = numPlayers;
        generateMaze();
    }
    public Maze(){
        this.width = 10;
        this.height = 10;
        this.numPlayers = 1;
        //rooms is set in generateMaze()
        generateMaze();
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
