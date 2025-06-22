




import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectFourPanel extends JPanel {
    private ConnectFourGame game;
    private final int CELL_SIZE = 80;
    private final int MARGIN = 10;
    
    public ConnectFourPanel(ConnectFourGame game) {
        this.game = game;
        setPreferredSize(new Dimension(
            game.getCols() * CELL_SIZE + MARGIN * 2,
            game.getRows() * CELL_SIZE + MARGIN * 2 + 60
        ));
        setBackground(Color.BLUE);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!game.isGameWon() && !game.isGameTie()) {
                    int col = (e.getX() - MARGIN) / CELL_SIZE;
                    if (col >= 0 && col < game.getCols()) {
                        game.makeMove(col);
                        repaint();
                    }
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawBoard(g2d);
        drawPieces(g2d);
        drawStatus(g2d);
    }
    
    private void drawBoard(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(MARGIN, MARGIN + 50, 
                  game.getCols() * CELL_SIZE, 
                  game.getRows() * CELL_SIZE);
        
        g.setColor(Color.WHITE);
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                int x = MARGIN + col * CELL_SIZE + CELL_SIZE / 2;
                int y = MARGIN + 50 + row * CELL_SIZE + CELL_SIZE / 2;
                g.fillOval(x - 30, y - 30, 60, 60);
            }
        }
    }
    
    private void drawPieces(Graphics2D g) {
        char[][] board = game.getBoard();
        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                if (board[row][col] != ' ') {
                    int x = MARGIN + col * CELL_SIZE + CELL_SIZE / 2;
                    int y = MARGIN + 50 + row * CELL_SIZE + CELL_SIZE / 2;
                    
                    if (board[row][col] == 'R') {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.YELLOW);
                    }
                    g.fillOval(x - 30, y - 30, 60, 60);
                }
            }
        }
    }
    
    private void drawStatus(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        
        String status;
        if (game.isGameWon()) {
            char winner = game.getCurrentPlayer();
            String winnerName = (winner == 'R') ? "Red" : "Yellow";
            status = winnerName + " Player Wins!";
        } else if (game.isGameTie()) {
            status = "Game is a Tie!";
        } else {
            char current = game.getCurrentPlayer();
            String currentName = (current == 'R') ? "Red" : "Yellow";
            status = currentName + " Player's Turn";
        }
        
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(status)) / 2;
        g.drawString(status, x, 30);
    }
} 