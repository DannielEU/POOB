import javax.swing.JOptionPane;
import java.util.ArrayList;

public class KalahGame {
    private Pit[] board;
    private boolean playerTurn;

    public KalahGame() {
        initializeGame();
    }

    public void initializeGame() {
        board = new Pit[14];
        for (int i = 0; i < 14; i++) {
            if (i == 6 || i == 13) {
                board[i] = new Pit(true, i < 7);
            } else {
                board[i] = new Pit(false, i < 7);
                board[i].putSeeds(4);
            }
        }
        organizeBoard();
        playerTurn = true;
        makeVisible();
        JOptionPane.showMessageDialog(null, "Juego iniciado. Turno del jugador N");
    }

    public boolean makeMove(int pitIndex) {
        if ((playerTurn && (pitIndex < 0 || pitIndex > 5)) || (!playerTurn && (pitIndex < 7 || pitIndex > 12))) {
            JOptionPane.showMessageDialog(null, "Movimiento inválido. Elige un pozo válido de tu lado.");
            return false;
        }

        int seeds = board[pitIndex].seeds();
        if (seeds == 0) {
            JOptionPane.showMessageDialog(null, "No hay semillas en esta casa. Elige otra.");
            return false;
        }

        ArrayList<String> seedColors = board[pitIndex].removeSeedsAndGetColors();
        int currentIndex = pitIndex;

        while (!seedColors.isEmpty()) {
            currentIndex = (currentIndex + 1) % 14;
            if ((playerTurn && currentIndex == 13) || (!playerTurn && currentIndex == 6)) {
                continue;
            }
            board[currentIndex].putSeed(seedColors.remove(0));
        }

        boolean extraTurn = (playerTurn && currentIndex == 6) || (!playerTurn && currentIndex == 13);
        if (!extraTurn && board[currentIndex].seeds() == 1 && ((playerTurn && currentIndex < 6) || (!playerTurn && currentIndex > 6 && currentIndex < 13))) {
            int oppositeIndex = 12 - currentIndex;
            int capturedSeeds = board[oppositeIndex].seeds();
            if (capturedSeeds > 0) {
                ArrayList<String> capturedColors = board[oppositeIndex].removeSeedsAndGetColors();
                int storeIndex = playerTurn ? 6 : 13;
                board[storeIndex].putSeeds(1);
                for (String color : capturedColors) {
                    board[storeIndex].putSeed(color);
                }
                JOptionPane.showMessageDialog(null, "¡Captura realizada!");
            }
        }

        if (!extraTurn) {
            playerTurn = !playerTurn;
        }

        checkWinCondition();
        return true;
    }

    public void checkWinCondition() {
        int northSeeds = 0, southSeeds = 0;
        for (int i = 0; i < 6; i++) northSeeds += board[i].seeds();
        for (int i = 7; i < 13; i++) southSeeds += board[i].seeds();

        if (northSeeds == 0 || southSeeds == 0) {
            for (int i = 0; i < 6; i++) {
                board[6].putSeeds(board[i].seeds());
                board[i].removeSeeds(board[i].seeds());
            }
            for (int i = 7; i < 13; i++) {
                board[13].putSeeds(board[i].seeds());
                board[i].removeSeeds(board[i].seeds());
            }
            
            int northStore = board[6].seeds();
            int southStore = board[13].seeds();
            String winner = northStore > southStore ? "N" : (northStore < southStore ? "S" : "Empate");
            JOptionPane.showMessageDialog(null, "Juego terminado. Ganador: " + winner);
            restartGame();
        }
    }

    public void restartGame() {
        initializeGame();
    }

    private void organizeBoard() {
        int xStart = 200, yStart = 200, spacing = 95;
        for (int i = 0; i < 6; i++) {
            board[i].moveTo(xStart + (i * spacing), yStart);
        }
        board[6].moveTo(xStart + (6 * spacing), yStart);
        for (int i = 7; i < 13; i++) {
            board[i].moveTo(xStart + ((12 - i) * spacing), yStart + spacing);
        }
        board[13].moveTo(xStart - spacing, yStart);
    }
    
    private void makeVisible() {
        for (Pit pit : board) {
            pit.makeVisible();
        }
    }
}







