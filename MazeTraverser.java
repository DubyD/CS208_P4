public class MazeTraverser {
    public MazeTraverser(MazeRecorder[] freespace,MazeRecorder[][] movement, char rotate) {
        int i =0;
        int j =0;
        while(!(i== movement.length-1 && j==movement[0].length-2)) {
            if (rotate == 's') {
                int temp = i + 1;
                if (movement[temp][j].equals(freespace)) {
                    i++;
                }
            } else if (rotate == 'd') {
                int temp = j + 1;
                if (movement[i][temp].equals(freespace)) {
                    j++;
                }
            } else if (rotate == 'w') {
                int temp = i - 1;
                if (movement[temp][j].equals(freespace)) {
                    i--;
                }
            } else if (rotate == 'a') {
                int temp = j - 1;
                if (movement[i][temp].equals(freespace)) {
                    j--;
                }
            }
        }
    }
}
