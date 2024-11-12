import java.util.LinkedList;

public class Mapp {
    // Define an Entry class to hold key-value pairs
    private static class Entry {
        Player key;
        RoomNode value;

        Entry(Player key, RoomNode value) {
            this.key = key;
            this.value = value;
        }
    }

    // Array of linked lists for buckets
    private LinkedList<Entry>[] table;
    private int size;

    // Constructor
    public Mapp(int capacity) {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<Entry>();
        }
        size = 0;
    }

    // Hash function
    private int getBucketIndex(Player key) {
        return Math.abs(key.hashCode() % table.length);
    }

    // Insert or update a key-value pair
    public void put(Player key, RoomNode value) {
        int index = getBucketIndex(key);
        LinkedList<Entry> bucket = table[index];

        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update value if key already exists
                return;
            }
        }
        
        // If key is not found, add a new entry
        bucket.add(new Entry(key, value));
        size++;
    }

    // Get the value for a given key
    public RoomNode get(Player key) {
        int index = getBucketIndex(key);
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
        int index = getBucketIndex(key);
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
        int index = getBucketIndex(key);
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
}
