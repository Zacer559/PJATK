public class TicTacToe {
    //changed to final
    public final StringBuffer board;

    public TicTacToe(String s) {
        board = new StringBuffer(s);
    }

    public TicTacToe(String s, int position, char player) {
        board = new StringBuffer(s);
        board.setCharAt(position, player);
    }

    public int suggestMove(char player) {
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-') {
                TicTacToe game = makeMove(i, player);
                if (game.winner() == player)
                    return i;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '-')
                return i;
        }

        return -1;
    }

    public TicTacToe makeMove(int i, char player) {
        return new TicTacToe(board.toString(), i, player);
    }

    // division of one big method to few smaller ones.
    //1 vertical win
    public char verticalWinner() {
        for (int i = 0; i < 3; ++i) {
            if (board.charAt(i) != '-'
                    && board.charAt(i + 3) == board.charAt(i)
                    && board.charAt(i + 6) == board.charAt(i))
                return board.charAt(i);
        }
        return '-';
    }

    //2 horizontal win
    public char horizontalWinner() {
        for (int i = 0; i < 9; i += 3) {
            if (board.charAt(i) != '-'
                    && board.charAt(i + 1) == board.charAt(i)
                    && board.charAt(i + 2) == board.charAt(i))
                return board.charAt(i);
        }
        return '-';
    }

    //3 diagonal win
    public char diagonalWinner() {
        if (board.charAt(0) != '-' && board.charAt(4) == board.charAt(0)
                && board.charAt(8) == board.charAt(0))
            return board.charAt(0);
        if (board.charAt(2) != '-' && board.charAt(4) == board.charAt(2)
                && board.charAt(6) == board.charAt(2))
            return board.charAt(2);
        return '-';
    }

    // new winner function
    public char winner() {
        if (verticalWinner() != '-') {
            return verticalWinner();
        } else if (horizontalWinner() != '-') {
            return horizontalWinner();
        }
        return diagonalWinner();
    }
}
