import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeTest {


    @Test
    public void testDefaultMove() {
        TicTacToe game = new TicTacToe("XOXOX-OXO");
        assertEquals(5, game.suggestMove('X'));

        game = new TicTacToe("XOXOXOOX-");
        assertEquals(8, game.suggestMove('O'));

        game = new TicTacToe("---------");
        assertEquals(0, game.suggestMove('X'));

        game = new TicTacToe("XXXXXXXXX");
        assertEquals(-1, game.suggestMove('X'));
    }

    @Test
    public void testFindWinningMove() {
        TicTacToe game = new TicTacToe("XO-XX-OO-");
        assertEquals(5, game.suggestMove('X'));
        assertEquals(8, game.suggestMove('O'));
    }

    @Test
    public void testWinConditions() {
        assertWinner("---XXX---", 'X');
        assertWinner("XXX------", 'X');
        assertWinner("------XXX", 'X');
        assertWinner("X--X--X--", 'X');
        assertWinner("-X--X--X-", 'X');
        assertWinner("--X--X--X", 'X');
        assertWinner("X---X---X", 'X');
        assertWinner("--X-X-X--", 'X');

        assertWinner("---OOO---", 'O');
        assertWinner("OOO------", 'O');
        assertWinner("------OOO", 'O');
        assertWinner("O--O--O--", 'O');
        assertWinner("-O--O--O-", 'O');
        assertWinner("--O--O--O", 'O');
        assertWinner("O---O---O", 'O');
        assertWinner("--O-O-O--", 'O');

        assertWinner("---------", '-');
        assertWinner("XXOOOXXOO", '-');
    }

    //removed @Test
    private void assertWinner(String s, char w) {
        TicTacToe game = new TicTacToe(s);
        assertEquals(w, game.winner());
    }
}