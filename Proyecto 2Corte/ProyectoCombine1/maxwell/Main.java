package maxwell;
import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            { 15, 10, 1, -2}
            ,{ 35, 10, -3, 2},
            { 15, 10, 2, -2}
            ,{ -65, 16, -3, 1},
            { 15, 10, 2, -2}
            ,{ -265, 16, -3, 1}
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 1, 2, particlesData);
        //public MaxwellContainer           (int h, int w, int d, int b, int r, int[][] particlesData)
        JOptionPane.showMessageDialog(null, "¡Juego Iniciado!");
        container.makeVisible();
        container.start(10000);
        
    }
}

