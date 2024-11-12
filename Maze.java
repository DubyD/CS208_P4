/**
 * @author Will Duby
 * @author Sukhdeep
 * @author Gus Warmington - some small initial contributions to genMaze
 *
 */

import java.util.HashMap;
import java.util.Random;

public class Maze {
    private RoomNode[][] maze;
    private final int WIDTH;
    private final int HEIGHT;
    private int numPlayers;
    private RoomNode start;
    private SingleLinkedList path;
    private HashMap<Player, RoomNode> playerRoomMap; // Map to track player locations

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
        this.playerRoomMap = null;
        this.end = null;
    }

    // If we get a working version we will create dynamic sizing
    public Maze(int width, int height, int numPlayers){
        this.WIDTH = width; //the same as GameScene
        this.HEIGHT = height; //the same as GameScene
        this.numPlayers = numPlayers;
        //this.playerRoomMap = new HashMap<>(); // Initialize the map
        //this.end = new RoomNode();
        // all other Vars are set in
        genMaze();
        genDoors();
        genTraps();
        /**this.path = genPath();
        this.start = this.path.getHead();
        this.path.getTail().setExit(this.end);*/
    }

    public Maze(int numOfPlayers){
        this.WIDTH = 5;
        this.HEIGHT = 5;
        this.numPlayers = numOfPlayers;
        this.playerRoomMap = new HashMap<>(); // Initialize the map
        this.end = new RoomNode();
        /**genMaze();
        this.path = genPath();
        this.start = this.path.getHead();
        this.path.getTail().setExit(this.end);*/
        genDoors();
        genTraps();

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
                //System.out.println(y + "," + x +" Assigned maze[][] RoomNode: x=" + maze[y][x].getX() + ", y=" + maze[y][x].getY());

            }
        }
        genPath();
        int x = (WIDTH/2);
        start = maze[0][x];
        for(int i = 0; i < numPlayers; i++){
            Player player = new Player();
            player.setRoom(start);
            start.addPlayer(player);
        }
    }
    private void genTraps(){
        System.out.println("Generating traps...");
        for(int i = 0; i < WIDTH - 1; i++){
            for(int j = 0; j < HEIGHT - 1; j++){
                //if(!path.inList(maze[j][i])){

                Random rand = new Random();
                int x = rand.nextInt(10);
                if(x > 7){
                    maze[j][i].setTrap();
                }

                //}
            }
        }
    }
    private void genDoors() {
        System.out.println("Generating doors...");
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
        System.out.print(i + " " + j +"\n");
            // Creates the path
        int lastRoom = 0;
        System.out.println(maze[j][i].getXIndex() + " " + maze[j][i].getYIndex() + "\n");
        SingleLinkedList temp = new SingleLinkedList();
        boolean looping = true;
        while(looping){
            int reRoll = lastRoom;
            lastRoom = dirCheck(maze[j][i], lastRoom);
            System.out.print(maze[j][i].getXIndex() + " " + maze[j][i].getYIndex() + " set path " + lastRoom+ "\n");
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
                if(temp.inList(maze[j-1][i])){
                    lastRoom = reRoll;
                    continue;
                }
                j -= 1;
            }
            if(lastRoom == 3){
                if(temp.inList(maze[j][i+1])){
                    lastRoom = reRoll;
                    continue;
                }
                i += 1;
            }
            if(lastRoom == 4){
                if(temp.inList(maze[j][i-1])){
                    lastRoom = reRoll;
                    continue;
                }
                i -= 1;
            }

            //temp.insertAtTail(maze[j][i]);

        }
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
        nextRoom = 1 + rand.nextInt(4);
        // South
        if(nextRoom == 1){
            if(lastRoom == 2){
                return 0;
            }
            // Creates Exit door
            if(room.getYIndex() + 1 > HEIGHT - 1){
                return -1;
            }
            return nextRoom;
        }
        // North
        if(nextRoom == 2){
            if(lastRoom == 1){
                return 0;
            }
            if(room.getYIndex() - 1 < 0){
                return 1;
            }
            return nextRoom;
        }
        // East
        if(nextRoom == 3){
            if(lastRoom == 4){
                return 0;
            }
            if(room.getXIndex() + 1 > WIDTH - 1){
                return 4;
            }
            return nextRoom;
        }
        // West
        if(nextRoom == 4){
            if(lastRoom == 3){
                return 0;
            }
            if(room.getXIndex() - 1 < 0){
                return 3;
            }
            return nextRoom;
        }
        return lastRoom;
    }

    ///Adds a player to the specified room
    /// @param player The player to be added to the room
     /// @param room the room to add the player to
    public void addPlayerToRoom(Player player, RoomNode room) {
        playerRoomMap.put(player, room);
    }



    ///@param p The player that wishes to move
     /// @param direction the direction the player wishes to move
     /// 0 = up, 1 = right, 2 = down, 3 = left
     /// @return true if successful, false if p cannot move to desired destination
    
     /*public boolean travel(Player p, int direction) {
        Door[] doors = p.getRoom().getDoors();
        if(doors[direction] != null){
            RoomNode newRoom = doors[direction].getDestination();

             p.setRoom(newRoom);
             playerRoomMap.put(p, newRoom); // Update the player’s room in the map

             return true;
        }
        else{
            return false;
        }

    }
    */
    public boolean travel(Player p, int direction) {
        // First, remove the player from the current room
        p.getRoom().leavingRoom(p);
    
        // Check if the move is possible based on available doors
        Door[] doors = p.getRoom().getDoors();
        if (doors[direction] != null) {
            RoomNode newRoom = doors[direction].getDestination();
    
            // Move the player to the new room
            p.setRoom(newRoom);
            playerRoomMap.put(p, newRoom); // Update the player's room in the map
    
            // Add player to the new room
            newRoom.addPlayer(p);
            return true;
        } else {
            return false;
        }
    }
    
    
    /// @param p The player to get the position of
    /// @return the room the player is currently in 
    public RoomNode getPlayerPosition(Player p){
        return playerRoomMap.get(p);
    }
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
        return path.getTail();
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


}
