/**
 *
 */

public class TurnTaker{


    private Maze gameBoard;
    private static int turnsTaken;

    public TurnTaker(){
        gameBoard = null;
        turnsTaken = 0;
    }

    public TurnTaker(Maze gameBoard){
        this.gameBoard = gameBoard;
        turnsTaken = 0;
    }
}
