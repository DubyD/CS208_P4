import java.util.LinkedList;

public class Mapp {
        // Define an Entry class to hold key-value pairs
    private static class Entry {
        RoomNode value;
        boolean clean;
            //Used to hold rooms
        public Entry(RoomNode value) {
            this.value = value;
            this.clean = false;
        }
            //Used for empty from start Buckets
        public Entry(Boolean isEmpty){
            this.value = null;
            this.clean = isEmpty;
        }
            //Used to empty buckets
        public Entry(){
            this.value = null;
            this.clean = false;
        }
    }

        // Array of linked lists for buckets
    private Entry[] table;
    private int size;

        // Constructor
    public Mapp(Maze maze) {
        size = maze.getHeight() * maze.getWidth();
        table = genBuckets(maze.getGame(), size);
    }
        // sets up the buckets as the mazeRooms
    private Entry[] genBuckets(RoomNode[][] maze, int size){
        Entry[] temp = new Entry[size];
            //initialized empty from start
        for(int i = 0; i < size; i++){
            temp[i] = new Entry(true);
        }

            //Connecting rooms to Buckets
        int i = 0;
        int j = 0;
        for(int i = 0; i < maze[0].length; i++){
            for(int j = 0; j < maze.length; j++){
                int hash = maze[j][i].hashCode()
                int collisionCount = 0;
                boolean collision = true;
                while(collision){

                    if(!isEmpty(getBucketIndex(hash * offset(collisionCount)))){
                        collisionCount++;
                        continue;
                    }
                    temp[getBucketIndex(maze.hashCode() * offset(collisionCount))].value = maze[j][i];
                }


            }
        }
        return temp;
    }
        // Hash function
    private int getBucketIndex(int hashCode) {
        return Math.abs(hashCode % table.length);
    }
        // Used to calculate any collisions
    private int offset(int numOfCollisions){
        return 31 * numOfCollisions;
    }
        // Used for player allocation
    private boolean isEmpty(int hashCode){
        return table[getBucketIndex(hashCode)].value == null;
    }
        // Used for room search
    private boolean isEmptyFromStart(int hashCode){
        return table[getBucketIndex(hashCode)].clean;
    }

        // Checks if the rooms x and y matches the parameter x and y
    private boolean checkCoordinates(int x, int y, int hash){
        RoomNode room = table[getBucketIndex(hash)].value;
        return  room.getXIndex() == x &&
                room.getYIndex() == y;
    }
        // Used for the hashcode checking
    private int getHashCode(int x, int y){
        final int prime = 31; //prime number used to avoid collisions
        int result = 1;
        result = result * prime + x;
        result = result * prime + y;
        return result;
    }
        // Used to get the room with this x and y coordinates
    public RoomNode getRoom(int x, int y) {
        int collisions = 0;
        while(true){
            if(checkCoordinates(x, y, getHashCode(x, y) * offset(collisions))){
                return table[getBucketIndex(getHashCode(x, y) * offset(collisions))].value;
            }
            collisions++;
        }
    }

        // Insert or update a key-value pair
    public void put(Player key, RoomNode oldRoom, int direction) {
        int collisions = 0;
        while(true){
            if(checkCoordinates(key.getX(), key.getY(), key.hashCode() * offset(collisions))){

            }
        }
    }
    public RoomNode get(){

    }
    /**
     * Working space, I need to check and possibly
     * adapt new logic to these methods

    // Get the value for a given key
    public RoomNode get(Player key) {
        int index = getBucketIndex(key.hashCode());
        LinkedList<Entry> bucket = table[index];

        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Key not found
    }

    // Remove a key-value pair
    public boolean remove(Player key) {
        int index = getBucketIndex(key.hashCode());
        LinkedList<Entry> bucket = table[index];

        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                return true;
            }
        }
        return false; // Key not found
    }

    // Check if the map contains a given key
    public boolean containsKey(Player key) {
        int index = getBucketIndex(key.hashCode());
        LinkedList<Entry> bucket = table[index];

        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    // Return the number of key-value pairs in the map
    public int size() {
        return size;
    }

    // Clear all entries in the map
    public void clear() {
        for (LinkedList<Entry> bucket : table) {
            bucket.clear();
        }
        size = 0;
    }
     */
}
