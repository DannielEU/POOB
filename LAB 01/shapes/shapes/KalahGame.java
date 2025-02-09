import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
/**
 * The kalah game.
 * 
 * @authors  Daniel Useche & Daniel Patiño
 * @version 1.0  (February 2025)
 */
public class KalahGame {
    private Pit[] board;
    private boolean playerTurn;
    /**
     * This method will call the method to initialize the game. 
     */   
    public KalahGame() {
        initializeGame();
    }
    /**
     * This method will initialize the game, adding the board and the seeds for the game.
     */
    public void initializeGame() {
        board = new Pit[14];
        for (int i = 0; i < 14; i++) {
            if (i == 6 || i == 13) {
                board[i] = new Pit(true, i < 7);
            } else {
                board[i] = new Pit(false, i < 7);
                board[i].putSeeds(3);
            }
        }
        organizeBoard();
        playerTurn = true;
        makeVisible();
        JOptionPane.showMessageDialog(null, "Juego iniciado. Turno del jugador N");
    }
    /**
     * This method will establish the rules of the game, the movements and in general the gameplay of the game.
     */ 
    public boolean makeMove(int pitIndex) {
        if ((playerTurn && (pitIndex < 0 || pitIndex > 5)) || (!playerTurn && (pitIndex < 7 || pitIndex > 12))) {
            JOptionPane.showMessageDialog(null, "Movimiento inválido. Debes jugar en tu lado.");
            return false;
        }
        int seeds = board[pitIndex].seeds();
        if (seeds == 0) {
            JOptionPane.showMessageDialog(null, "Mejor pideme dividir por cero. !No hay semillas en esta casa¡. Busca otra.");
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
                JOptionPane.showMessageDialog(null, "¡Captura realizada! semillas secuestradas =)");
            }
        }
        if (!extraTurn) {
            playerTurn = !playerTurn;
        }
        checkWinCondition();
        return true;
    }
    /**
     *  This method will check the conditions that must be met for a player to win.
     */
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
            JOptionPane.showMessageDialog(null, "Juego terminado. El dulce Ganador: " + winner);
            restartGame();
        }
    }
    /**
     * This method will restart the game calling back the initializeGame method.
     */   
    public void restartGame() {
        initializeGame();
    }
    /*
     * This method will organize the board.
     */  
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
    /*
     * This method will make visible the game.
     */  
    private void makeVisible() {
        for (Pit pit : board) {
            pit.makeVisible();
        }
    }
        /**
     * This method will show the status of the game, showing the seeds in each warehouse.
     */
    public void mostrarEstadoJuego() {
        StringBuilder estado = new StringBuilder("Estado actual del juego:\n\n");
    
        // Mostrar casas del sur (jugador sur)
        estado.append("Sur:\n");
        for (int i = 0; i < 6; i++) {
            estado.append("Casa ").append(i).append(": ").append(board[i].seeds()).append(" semillas\n");
        }
        estado.append("Almacén Sur (6): ").append(board[6].seeds()).append(" semillas\n\n");
    
        // Mostrar casas del norte (jugador norte)
        estado.append("Norte:\n");
        for (int i = 12; i > 6; i--) {
            estado.append("Casa ").append(i).append(": ").append(board[i].seeds()).append(" semillas\n");
        }
        estado.append("Almacén Norte (13): ").append(board[13].seeds()).append(" semillas");
    
        JOptionPane.showMessageDialog(null, estado.toString(), "Estado del Juego", JOptionPane.INFORMATION_MESSAGE);
    }
        /**
     * This method will create a machine that can play automatically, making moves and some comments.
     */
    public void machineMove() { 
        if (!playerTurn) {  
            ArrayList<Integer> validMoves = new ArrayList<>();
            for (int i = 7; i < 13; i++) {
                if (board[i].seeds() > 0) {
                    validMoves.add(i);
                }
            }
            if (!validMoves.isEmpty()) {
                int chosenMove = validMoves.get(new Random().nextInt(validMoves.size()));
                makeMove(chosenMove);
                JOptionPane.showMessageDialog(null, "La máquina jugó en la casa " + chosenMove);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Pero si es tu turno tontin. =)");
        }
    }
}
