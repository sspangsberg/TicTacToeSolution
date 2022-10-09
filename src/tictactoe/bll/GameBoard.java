package tictactoe.bll;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    final int ROWS = 3, COLUMNS = 3;
    private int[][] board = new int[ROWS][COLUMNS];

    /**      COLUMNS
     *         012
     *       0 ---
     *  ROWS 1 ---
     *       2 ---
     *
     */


    public GameBoard() {
        newGame();
    }

    /**
     * Defaults to a draw
     */
    private int winnerId = -1;

    /**
     * The current playerId
     */
    private int currentPlayerId = 0;

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return currentPlayerId;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //char marker = currentPlayerId == 0? 'X': 'O';
        if (board[row][col] == -1) {// Field is empty - we can make our move
            board[row][col] = currentPlayerId;

            if (currentPlayerId == 0)
                currentPlayerId = 1;
            else
                currentPlayerId = 0;

            return true;
        }
        else {
            return false;
        }
    }

    public boolean isGameOver()
    {
        boolean boardIsFull = boardIsFull();
        boolean winnerExist = checkForWinner();

        if (boardIsFull || winnerExist)
            return true;
        else
            return false;
    }

    /**
     *
     * @return
     */
    private boolean boardIsFull() {

        // Check if board is full
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == -1) { // If we find one empty spot, it's not full
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check for a winner in the board
     * Saves the winnerId
     *
     * @return true if we have a winner, false otherwise
     */
    private boolean checkForWinner() {
        // Check if we have a winner or a draw
        // Horizontal win check
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != -1) {
                winnerId = board[row][0];
                return true;
            }
        }

        // Vertical win check
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != -1) {
                winnerId = board[0][col];
                return true;
            }
        }

        // Diagonal win check
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != -1) {
            winnerId = board[0][0];
            return true;
        }
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != -1) {
            winnerId = board[0][2];
            return true;
        }

        return false;
    }


    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        if (checkForWinner())
            return winnerId;
        else return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = -1;
            }
        }
    }
}
