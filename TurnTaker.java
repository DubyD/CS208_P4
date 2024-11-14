/**
 * @author: Will Duby
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

    public int getTurnsTaken(){
        return turnsTaken;
    }
    public void setTurnsTaken(){
        turnsTaken++;
    }

    public void takeATurn(){

        for(Player p : gameBoard.getPlayers()){

        }

    }

    @Override
    public String toString() {
        return "TurnTaker [gameBoard=" + gameBoard + ", turnsTaken=" + turnsTaken + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TurnTaker other = (TurnTaker) obj;
        if (gameBoard == null) {
            if (other.gameBoard != null)
                return false;
        } else if (!gameBoard.equals(other.gameBoard))
            return false;
        return true;
    }
}
