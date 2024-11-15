/**
 * @author Will Duby
 */
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
        table = new Entry[size];
        genBuckets(maze.getGame(), size);
    }
        // sets up the buckets as each of the mazeRooms
    private void genBuckets(RoomNode[][] maze, int size){
        table = new Entry[size];
            //initialized empty from start
        for(int i = 0; i < size; i++){
            table[i] = new Entry(true);
        }

            //Connecting rooms to Buckets
        int x = 0;
        for(int i = 0; i < maze[0].length; i++){
            for(int j = 0; j < maze.length; j++){
                int hash = maze[j][i].hashCode();
                int collisionCount = 0;
                boolean collision = true;
                while(collision){

                    //System.out.println(table[getBucketIndex(hash + offset(collisionCount))].clean);
                    if(isEmpty(getBucketIndex(hash + offset(collisionCount)))){
                        System.out.println("Item number: " + x);
                        System.out.println("Collisions: " + collisionCount);
                        System.out.println("Empty bucket");
                        table[getBucketIndex(hash + offset(collisionCount))] = new Entry(maze[j][i]);
                        collision = false;
                        x++;
                    }
                    collisionCount++;
                }
            }
        }
    }
        // Hash function
    private int getBucketIndex(int hashCode) {
        //System.out.println(hashCode % table.length);
        return Math.abs(hashCode % table.length);
    }
    private int offset(int collisons){
        return collisons * (int) Math.round(Math.sqrt(size));
    }

        // Used for player allocation
        // The offset must be calculated before getting here
    private boolean isEmpty(int hashCode){
        return table[getBucketIndex(hashCode)].value == null;
    }
        // Used for room search
        // The offset must be calculated before getting here
    private boolean isEmptyFromStart(int hashCode){
        return table[getBucketIndex(hashCode)].clean;
    }

        // Checks if the rooms x and y matches the parameter x and y
        // The offset must be calculated before getting here
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
    public RoomNode getRoom(int x, int y){
        int collisions = 0;
        while(true){
            if(checkCoordinates(x, y, getHashCode(x, y) + offset(collisions))){
                return table[getBucketIndex(getHashCode(x, y) + offset(collisions))].value;
            }
            collisions++;
            if(collisions == table.length){
                return null;
            }
        }
    }

        // Insert or update a key-value pair
    public boolean put(Player key, int direction) {
        int collisions = 0;
        int x = key.getX();
        int y = key.getY();
        if(direction == 0){
            y -= 1;
        }
        if(direction == 1){
            x += 1;
        }
        if(direction == 2){
            y += 1;
        }
        if(direction == 3){
            x -= 1;
        }
        while(true){
            if(checkCoordinates(x, y, key.hashCode() + offset(collisions))){
                return getRoom(x,y).addPlayer(key);

            }
            collisions++;
            if(collisions == table.length){
                return false;
            }
        }
    }


    // Get the room a player is in
    public RoomNode get(Player key) {
        int collisions = 0;
        while(true){
            if(checkCoordinates(key.getX(), key.getY(), key.hashCode() + offset(collisions))){
                int index = getBucketIndex(key.hashCode() + offset(collisions));
                return table[index].value;
            }
            collisions++;
            if(collisions == table.length){
                return null;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Mapp other = (Mapp) obj;
        if (size != other.size) return false;
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                if (other.table[i] != null) return false;
            } else if (other.table[i] == null) {
                return false;
            } else {
                if (table[i].value != other.table[i].value || 
                    table[i].clean != other.table[i].clean) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mapp{size=").append(size).append(", table=[");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                sb.append("\n  [").append(i).append("]: ");
                if (table[i].value != null) {
                    sb.append("Room at (")
                      .append(table[i].value.getXIndex())
                      .append(",")
                      .append(table[i].value.getYIndex())
                      .append(")");
                } else {
                    sb.append("Empty");
                }
                sb.append(", clean=").append(table[i].clean);
            }
        }
        sb.append("\n]}");
        return sb.toString();
    }
    /**
    // Remove a key-value pair
    public boolean remove(Player key) {
        int collisions = 0;
        while(true){
            if(checkCoordinates(key.getX(), key.getY(), key.hashCode() + offset(collisions))){
                Entry bucket = table[getBucketIndex(key.hashCode() + offset(collisions))];
                return bucket.value.removePlayer(key);
            }
            collisions++;
            if(collisions == table.length){
                return false;
            }
        }


         // Key not found
    }*/
    /**
     * Working space, I need to check and possibly
     * adapt new logic to these methods

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
