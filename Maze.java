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
        this.width = 5;
        this.height = 5;
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
    ///@return true if successful
    public boolean generateMaze(){
        rooms = new RoomNode[height][width];

        for(int y = 0; y < height; y++){

            for(int x = 0; x < width; x++){
                if(y == 0){ //first level, only one node
                    if(x != width/2){
                        rooms[y][x] = null;
                    }
                    else{ //start in middle of length
                        rooms[y][x] = start = new RoomNode(this, 1, x, y, false); //starting room
                    }
                }
                else if(y == height - 1){
                    //last row, decides the exit
                }
                else{
                    //All other rooms

                }
            }
        }
        return true;
    }


}
