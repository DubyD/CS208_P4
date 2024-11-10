/**
 * Author Will Duby, Sukhdeep
 */

import java.util.HashMap;
import java.util.Random;

public class Maze {
    private RoomNode[][] maze;
    private int width;
    private int height;
    private int numPlayers;
    private RoomNode start;
    private SingleLinkedList path;
    private HashMap<Player, RoomNode> playerRoomMap; // Map to track player locations

    // a room outside of the maze
    // can determine if a player has
    // completed the maze or not
    private RoomNode end;


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
        this.playerRoomMap = new HashMap<>(); // Initialize the map
        this.end = new RoomNode();
        // all other Vars are set in
        genMaze();
    }

    public Maze(int numOfPlayers){
        this.width = 4;
        this.height = 4;
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
        maze = new RoomNode[width][height];
        this.path = genPath();
        this.start = this.path.getHead();
        this.path.getTail().setExit(this.end);
        genDoors();
        genTraps();
    }
    private void genDoors() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                RoomNode room = maze[i][j];
                
                // Create doors in possible directions
                if (i > 0) room.setDoor(0, maze[i - 1][j]);     // Up
                if (j < height - 1) room.setDoor(1, maze[i][j + 1]); // Right
                if (i < width - 1) room.setDoor(2, maze[i + 1][j]); // Down
                if (j > 0) room.setDoor(3, maze[i][j - 1]);     // Left
            }
        }
    }
    
    /**
     * Ensures that a solid path and exit are generated
     */
    private SingleLinkedList genPath(){
        // for dynamic sizing
        int i;
        int j = 0;
        if(width % 2 == 1){
            i = (width + 1) / 2;
        }else{
            i = width / 2;
        }
            // Creates the path
        int lastRoom = 0;
        RoomNode lastRoomNode = maze[j][i];
        SingleLinkedList temp = new SingleLinkedList();
        boolean looping = true;
        while(looping){
            temp.insertAtTail(lastRoomNode);
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
        /**
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
    
     private boolean travel(Player p, int direction) {
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
    
    public RoomNode getNode(int x, int y){
            if(x >= 0 && x <= width && y >= 0 && y <= height) {//avoid index out of bounds
                return maze[y][x];
            }
            return null;

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



}
