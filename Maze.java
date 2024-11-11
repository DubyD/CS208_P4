/**
 * Author Will Duby, Sukhdeep
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
    }

    // If we get a working version we will create dynamic sizing
    public Maze(int width, int height, int numPlayers){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.numPlayers = numPlayers;
        //this.playerRoomMap = new HashMap<>(); // Initialize the map
        //this.end = new RoomNode();
        // all other Vars are set in
        genMaze();
    }

    public Maze(int numOfPlayers){
        this.WIDTH = 5;
        this.HEIGHT = 5;
        this.numPlayers = numOfPlayers;
        this.playerRoomMap = new HashMap<>(); // Initialize the map
        this.end = new RoomNode();
        // all other Vars are set in
        genMaze();

    }

    /**
     * Generates the Maze
     * Creates a path with an exit
     * then randomly assigns doors to rooms
     *
     */
    private void genMaze(){
        maze = new RoomNode[WIDTH][HEIGHT];
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[0].length ; x++) {
                //JLabel node = new JLabel("x: " + x + ", y: " + y);
                maze[y][x] = new RoomNode(x,y);

            }
        }
        start = maze[0][0];
        for(int i = 0; i < numPlayers; i++){
            Player player = new Player();
            player.setRoom(start);
            start.addPlayer(player);
        }

        //this.path = genPath();
        //this.start = this.path.getHead();
        //this.path.getTail().setExit(this.end);
        genDoors();
        //genTraps();
    }
    private void genTraps(){
        System.out.println("Generating traps...");
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                if(!path.inList(maze[i][j])){
                    Random rand = new Random();
                    int x = rand.nextInt(10);
                    if(x > 7){
                        maze[i][j].setTrap();
                    }
                }
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
        System.out.println("Generating path...");
        // for dynamic sizing
        int j = 0;
        int i = (WIDTH % 2 == 1) ? ((WIDTH + 1) / 2) : (WIDTH / 2); //changed to ternary for compression - gus

            // Creates the path
        int lastRoom = 0;
        RoomNode lastRoomNode = maze[j][i];
        SingleLinkedList temp = new SingleLinkedList();
        boolean looping = true;
        while(looping){
            //temp.insertAtTail(lastRoomNode);
            lastRoom = dirCheck(lastRoomNode, lastRoom);
            if(lastRoom == -1){
                looping = false;
            }

            if(lastRoom == 1){
                j += 1;
                lastRoomNode = maze[j][i];
            }
            if(lastRoom == 2){
                j -= 1;
                lastRoomNode = maze[j][i];
            }
            if(lastRoom == 3){
                i += 1;
                lastRoomNode = maze[j][i];
            }
            if(lastRoom == 4){
                i -= 1;
                lastRoomNode = maze[j][i];
            }

        }
        /*
         * if doors are randomly generated this will
         * ensure that there is a path to the exit

            // Ensure the Paths has doors
        RoomNode tempRoom = temp.getHead();
        while(tempRoom != null){
            tempRoom.compareNextRoom(tempRoom.getNextNode());
            tempRoom = tempRoom.getNextNode();
        }
         */
        return temp;
    }

    /**
     * Used in genPath()
     */
    private int dirCheck(RoomNode room, int lastRoom){
        Random rand = new Random();
        int nextRoom;
        nextRoom = 1 + rand.nextInt(4);
        // South
        if(nextRoom == 1){
            if(lastRoom == 2){
                return 2;
            }
            // Creates Exit door
            if(room.getY() + 1 > 5){
                return -1;
            }
            return nextRoom;
        }
        // North
        if(nextRoom == 2){
            if(lastRoom == 1){
                return 1;
            }
            if(room.getY() - 1 < 0){
                return 1;
            }
            return nextRoom;
        }
        // East
        if(nextRoom == 3){
            if(lastRoom == 4){
                return 4;
            }
            if(room.getX() + 1 > 5){
                return 4;
            }
            return nextRoom;
        }
        // West
        if(nextRoom == 4){
            if(lastRoom == 3){
                return 3;
            }
            if(room.getX() - 1 < 0){
                return 3;
            }
            return nextRoom;
        }
        return lastRoom;
    }

    // Adds a player to the specified room
    public void addPlayerToRoom(Player player, RoomNode room) {
        playerRoomMap.put(player, room);
    }



    ///@param p The player that wishes to move
     /// @param direction the direction the player wishes to move
     /// 0 = up, 1 = right, 2 = down, 3 = left
     /// @return true if successful, false if p cannot move to desired destination
    
     public boolean travel(Player p, int direction) {
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
    
    /// @param p The player to get the position of
    /// @return the room the player is currently in 
    public RoomNode getPlayerPosition(Player p){
        return playerRoomMap.get(p);
    }

    public RoomNode getNode(int x, int y){
//            if(x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT) {//avoid index out of bounds
        if(maze[y][x] == null){
            System.out.println("Null node!");
            maze[y][x] = new RoomNode(x, y);
        }
        System.out.println("Getting node (" + x + ", " + y +")");
        return maze[y][x];
    }

    public RoomNode getStart(){
        return start;
    }

    public RoomNode getFinish(){
        return path.getTail();
    }

    public int numPlayers(){
        return numPlayers;
    }
    public RoomNode[][] getMaze(){
        return maze;
    }
    public int getHeight(){
         return HEIGHT;
    }
    public int getWidth(){
         return WIDTH;
    }


}
