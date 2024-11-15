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
        for(int i = 0; i < maze[0].length; i++){
            for(int j = 0; j < maze.length; j++){

                int hash = maze[j][i].hashCode();
                int collisionCount = 0;
                boolean collision = true;
                while(collision){
                    if(isEmpty(getBucketIndex(hash + offset(collisionCount)))){
                        table[getBucketIndex(hash + offset(collisionCount))] = new Entry(maze[j][i]);
                        collision = false;
                    }
                    collisionCount++;
                }
            }
        }
    }
        // Hash function
    private int getBucketIndex(int hashCode) {
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
        // Checks if the rooms x and y matches the parameter x and y
        // The offset must be calculated before getting here
    private boolean checkCoordinates(int x, int y, int hash){
        RoomNode room = table[getBucketIndex(hash)].value;
        if(room != null){
            return  room.getXIndex() == x &&
                    room.getYIndex() == y;
        }
        return false;

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
            if(collisions == table.length || table[getBucketIndex(getHashCode(x, y) + offset(collisions))].clean){
                return null;
            }
        }
    }
    // Get the room a player is in
    public RoomNode get(Player key) {
        int collisions = 0;
        while(true){
            if(checkCoordinates(key.getX(), key.getY(), key.hashCode() + offset(collisions))){
                return table[getBucketIndex(key.hashCode() + offset(collisions))].value;
            }
            collisions++;
            if(collisions == table.length){
                return null;
            }
        }
    }
    // Remove a key-value pair
    public void remove(Player key) {
        int collisions = 0;
        boolean looping = true;
        while (looping) {
            if (checkCoordinates(key.getX(), key.getY(), key.hashCode() + offset(collisions))) {
                table[getBucketIndex(key.hashCode() + offset(collisions))].value.leavingRoom(key);
                looping = false;
            }
            collisions++;
            if (collisions == table.length) {
                looping = false;
            }
        }
    }
    public void removeRoom(RoomNode removal){
        int collisions = 0;
        boolean looping = true;
        while (looping) {
            if (checkCoordinates(removal.getX(), removal.getY(), removal.hashCode() + offset(collisions))) {
                table[getBucketIndex(removal.hashCode() + offset(collisions))] = new Entry();
                looping = false;
            }
            collisions++;
            if (collisions == table.length) {
                looping = false;
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

}
