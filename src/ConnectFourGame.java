




public class ConnectFourGame {
    private char[][] board;
    private int ROWS = 6;
    private int COLS = 7;
    private char currentPlayer = 'R';
    private boolean gameWon = false;
    private boolean gameTie = false;
    
    public ConnectFourGame() {
        board = new char[ROWS][COLS];
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    public boolean makeMove(int col) {
        if (col < 0 || col >= COLS || gameWon || gameTie) {
            return false;
        }
        
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                checkWin(row, col);
                checkTie();
                if (!gameWon && !gameTie) {
                    currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
                }
                return true;
            }
        }
        return false;
    }
    
    private void checkWin(int row, int col) {
        if (checkDirection(row, col, 0, 1) || 
            checkDirection(row, col, 1, 0) || 
            checkDirection(row, col, 1, 1) || 
            checkDirection(row, col, 1, -1)) {
            gameWon = true;
        }
    }
    
    private boolean checkDirection(int row, int col, int deltaRow, int deltaCol) {
        char player = board[row][col];
        int count = 1;
        
        for (int i = 1; i < 4; i++) {
            int newRow = row + i * deltaRow;
            int newCol = col + i * deltaCol;
            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS 
                && board[newRow][newCol] == player) {
                count++;
            } else {
                break;
            }
        }
        
        for (int i = 1; i < 4; i++) {
            int newRow = row - i * deltaRow;
            int newCol = col - i * deltaCol;
            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS 
                && board[newRow][newCol] == player) {
                count++;
            } else {
                break;
            }
        }
        
        return count >= 4;
    }
    
    private void checkTie() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == ' ') {
                return;
            }
        }
        gameTie = true;
    }
    
    public void resetGame() {
        gameWon = false;
        gameTie = false;
        currentPlayer = 'R';
        initializeBoard();
    }
    
    public char[][] getBoard() {
        return board;
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameWon() {
        return gameWon;
    }
    
    public boolean isGameTie() {
        return gameTie;
    }
    
    public int getRows() {
        return ROWS;
    }
    
    public int getCols() {
        return COLS;
    }
} 