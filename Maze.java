public class Maze {
    private RoomNode[][] rooms;
    public Maze(){

    }
    private boolean travel(Player p, int direction) {
        //direction -> 0 north, 1 east(right), 2 south, 3 west(left)
        switch (direction) {
            case 0:
                if (p.getRoom().getY() != 0) {
                    if (p.getRoom().getDoor() == 15 ||)
                } else {
                    return false;
                }

        }
    }
    public RoomNode getNode(int x, int y){
            if(x >= 0 && y >= 0) {
                return rooms[x][y];
            }
            return null;

    }


}
