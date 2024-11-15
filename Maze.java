/**
 * @author Will Duby
 * @author Sukhdeep
 * @author Gus Warmington - some small initial contributions to genMaze
 *
 */


import java.util.Random;
import java.util.Arrays;
import java.util.Objects;

public class Maze {
    private RoomNode[][] maze;
    private final int WIDTH;
    private final int HEIGHT;
    private int numPlayers;
    private RoomNode start;
    private SingleLinkedList path;
    private Mapp playerMapp; // Use an appropriate capacity as needed
    private Player[] players;

    // a room outside of the maze
    // can determine if a player has
    // completed the maze or not
    private RoomNode end;


    public Maze(){
        WIDTH = 5;
        HEIGHT = 5;
        this.numPlayers = 0;
        this.start = null;
        this.path = null;
        this.maze = null;
        this.playerMapp = null;
        this.end = null;
        this.playerMapp = null;
    }

    // If we get a working version we will create dynamic sizing
    public Maze(int width, int height, int numPlayers){
        this.WIDTH = width; //the same as GameScene
        this.HEIGHT = height; //the same as GameScene
        this.numPlayers = numPlayers;
        this.end = new RoomNode();
        //this.playerLocations = new HashMap<>(); // Initialize the map
        //this.end = new RoomNode();
        // all other Vars are set in
        genMaze();
        genDoors();
        genTraps();
        /**this.path = genPath();
        this.start = this.path.getHead();
        this.path.getTail().setExit(this.end);*/
        this.playerMapp = new Mapp(this);
    }

    public Maze(int numOfPlayers){
        this.WIDTH = 5;
        this.HEIGHT = 5;
        this.numPlayers = numOfPlayers;
        this.playerMapp = new Mapp(this); // Initialize the map
        this.end = new RoomNode();
        /**genMaze();
        this.path = genPath();
        this.start = this.path.getHead();
        this.path.getTail().setExit(this.end);*/
        genDoors();
        genTraps();
        this.playerMapp = new Mapp(this);
    }

    /**
     * Generates the Maze:
     * Assigns maze array (2D grid of RoomNodes) new RoomNodes with x and y values that are in accordance to their
     * array indices.
     * Creates a path with an exit
     * then randomly assigns doors to rooms
     *
     */
    private void genMaze(){
        maze = new RoomNode[HEIGHT][WIDTH];
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[0].length ; x++) {
                maze[y][x] = new RoomNode(x,y);
            }
        }
        genPath();
        int x = (WIDTH/2);
        int y = 0;
        start = maze[y][x];
        players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            Player player = new Player();
            player.setRoom(start);
            start.addPlayer(player);
            players[i] = player;
        }
    }
    private void genTraps(){
        for(int i = 0; i < WIDTH - 1; i++){
            for(int j = 0; j < HEIGHT - 1; j++){


                Random rand = new Random();
                int x = rand.nextInt(10);
                if(x > 4){
                    maze[j][i].setTrap();
                }
            }
        }
    }
    private void genDoors() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                RoomNode room = maze[i][j];

                // Create doors in possible directions
                if (i > 0) room.setDoor(0, maze[i - 1][j]);     // Up
                if (j < HEIGHT - 1) room.setDoor(1, maze[i][j + 1]); // Right
                if (i < WIDTH - 1) room.setDoor(2, maze[i + 1][j]); // Down
                if (j > 0) room.setDoor(3, maze[i][j - 1]);     // Left
            }
        }
    }
    
    /**
     * Ensures that a solid path and exit are generated
     */
    private SingleLinkedList genPath(){
        //System.out.println("Generating path...");
        // for dynamic sizing
        int j = 0;
        int i = (WIDTH / 2);
            // Creates the path
        int lastRoom = 0;
        SingleLinkedList temp = new SingleLinkedList();
        boolean looping = true;
        while(looping){
            int reRoll = lastRoom;
            lastRoom = dirCheck(maze[j][i], lastRoom);

                //If the direction would backtrack reroll dirCheck
            if(lastRoom == 0){
                lastRoom = reRoll;
                continue;
            }

            if(lastRoom == -1){
                looping = false;
            }

            if(lastRoom == 1){
                if(temp.inList(maze[j+1][i])){
                    lastRoom = reRoll;
                    continue;
                }
                j += 1;
            }
            if(lastRoom == 2){
                if(temp.inList(maze[j][i+1])){
                    lastRoom = reRoll;
                    continue;
                }
                i += 1;
            }
            if(lastRoom == 3){
                if(temp.inList(maze[j][i-1])){
                    lastRoom = reRoll;
                    continue;
                }
                i -= 1;
            }
        }
        temp.insertAtTail(maze[j][i]);
        temp.getTail().setExit(end);
        return temp;
    }

    /**
     * Used in genPath()
     */
    ///@param room The room node to check
     /// @param lastRoom the previous room
    private int dirCheck(RoomNode room, int lastRoom){
        Random rand = new Random();
        int nextRoom;
        nextRoom = 1 + rand.nextInt(3);
        // South
        if(nextRoom == 1){
            // Creates Exit door
            if(room.getYIndex() + 1 > HEIGHT - 1){
                return -1;
            }
            return nextRoom;
        }
        // East
        if(nextRoom == 2){
            if(lastRoom == 3 || room.getXIndex() + 1 > WIDTH - 1){
                return 0;
            }
            return nextRoom;
        }
        // West
        if(nextRoom == 3){
            if(lastRoom == 2 || room.getXIndex() - 1 < 0){
                return 0;
            }
            return nextRoom;
        }
        return lastRoom;
    }

    /**
     * This is done in the HashMap
    ///Adds a player to the specified room
    /// @param player The player to be added to the room
    /// @param room the room to add the player to
    public void addPlayerToRoom(Player player, RoomNode room) {
        room.addPlayer(player);
    }*/

    public Player[] getPlayers(){
        return players;
    }

    public RoomNode[][] getGame(){
        return maze;
    }
    public RoomNode getEndNode(){
        return end;
    }


    public boolean travel(Player p, int x, int y){

        if(playerMapp.getRoom(x,y) == null){
            return false;
        }

        RoomNode leaving = p.getRoom();
        RoomNode enteringRoom = playerMapp.getRoom(x,y);
        p.setRoom(enteringRoom);
        leaving.leavingRoom(p);

        return true;
    }

    /**
     * The player has refference to their position

    /// @param p The player to get the position of
    /// @return the room the player is currently in 
    public RoomNode getPlayerPosition(Player p) {
        return playerLocations.get(p);
    }*/
    
    ///@param x x coordinate of the node (relative to room layout, not the pixel coordinate)
     /// @param y coordinate of the node (same as above)
     /// @return the requested room node
    public RoomNode getNode(int x, int y){
        if(x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT) {//avoid index out of bounds
            if (maze[y][x] == null) { //avoid null if in bounds
                maze[y][x] = new RoomNode(x, y);
            }
            return maze[y][x];
        }
        else{
            return null;
        }
    }
    ///@return the starting node, to initially place players.
    public RoomNode getStart(){
        return start;
    }
    ///@return the final room, the exit node
    public RoomNode getFinish(){
        return end;
    }
    ///@return number of players, set from GameScene
    public int numPlayers(){
        return numPlayers;
    }
    ///@return the maze 2D array itself
    public RoomNode[][] getMaze(){
        return maze;
    }
    ///@return the HEIGHT constant, the amount of cells tall the house is
    public int getHeight(){
         return HEIGHT;
    }
    ///@return the WIDTH constant, the amount of cells tall the house is
    public int getWidth(){
         return WIDTH;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Maze other = (Maze) obj;
        return WIDTH == other.WIDTH &&
               HEIGHT == other.HEIGHT &&
               numPlayers == other.numPlayers &&
               Arrays.deepEquals(maze, other.maze) &&
               Objects.equals(start, other.start) &&
               Objects.equals(end, other.end) &&
               Objects.equals(path, other.path) &&
               Objects.equals(playerMapp, other.playerMapp) &&
               Arrays.equals(players, other.players);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maze{");
        sb.append("width=").append(WIDTH);
        sb.append(", height=").append(HEIGHT);
        sb.append(", numPlayers=").append(numPlayers);
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", maze=").append(Arrays.deepToString(maze));
        sb.append(", players=").append(Arrays.toString(players));
        sb.append("}");
        return sb.toString();
    }
}
