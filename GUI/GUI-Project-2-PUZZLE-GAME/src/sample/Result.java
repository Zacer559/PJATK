package sample;

public class Result {
    private int moves;
    private Timee time;

    public Result(int moves, Timee time) {
        this.moves = moves;
        this.time = time;

    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Timee getTime() {
        return time;
    }

    public void setTime(Timee time) {
        this.time = time;
    }
}
