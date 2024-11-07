public class MazeReader {
    public MazeReader(char[][] grid) {
        MazeRecorder[] mazenew = new MazeRecorder[grid.length * grid[0].length];
        System.out.println(grid.length +" by "+ grid[0].length);
        int sizerecorder=0;
        int sizeup=0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if((grid[i][j]) == '*'){
                    mazenew[sizerecorder] = new MazeRecorder(i,j);
                    sizerecorder++;
                }
            }
        }
        MazeRecorder[] recorder = new MazeRecorder[sizerecorder];
        System.out.println(sizerecorder);

        for(int i=0; i<sizerecorder; i++){
            recorder[i] = new MazeRecorder(mazenew[i].getHeightY(),mazenew[i].getWidthX());
        }
        for(int i=0; i< grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(sizerecorder>sizeup) {
                    if (recorder[sizeup].getHeightY() == i && recorder[sizeup].getWidthX() == j) {
                        System.out.print('*');
                        sizeup++;
                    }
                    else
                        System.out.print(' ');
                }

            }
            System.out.println();
        }

    }
    public static void main(String[] args) {
        MazeCreation maze = new MazeCreation(20);
        maze.solve();
        MazeReader mazeprint = new MazeReader(maze.getGrid());

        for(int i=0; i<maze.getGrid().length; i++){
            for(int j=0; j<maze.getGrid()[0].length; j++){
                System.out.print(maze.getGrid()[i][j]);
            }
            System.out.println();
        }

    }
}
