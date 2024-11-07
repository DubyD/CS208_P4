public class MazeRecorder {
    private int heightY;
    private int widthX;
    public MazeRecorder(int height, int width) {
        this.heightY = height;
        this.widthX = width;
    }
    public int getHeightY() {
        return this.heightY;
    }
    public int getWidthX() {
        return this.widthX;
    }

    public boolean equals(MazeRecorder[] freespace){
        int counter = 0;
        while(this.heightY != freespace[counter].getHeightY() && this.widthX != freespace[counter].getWidthX()){
            counter++;
            if(counter == freespace.length){
                return false;
            }
        }
        return true;
    }
}
