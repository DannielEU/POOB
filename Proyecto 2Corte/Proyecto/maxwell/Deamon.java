package maxwell;
import java.util.Random;
import shapes.*;
import java.util.Random;

/**
 * Esta clase contiene la creacion y caracteristicas del demonio.
 * 
 * @author Daniel Patiño & Daniel Useche
 * @version Version 1.1
 */

public class Deamon {
    protected Rectangle rectangle; 
    
    public Deamon(int deamonX, int deamonY) {
        rectangle = new Rectangle();
        this.settings(deamonX,deamonY);
    }
    
    /*
     * Metodo que define las caracteristicas del demonio.
     * 
     * @param deamonX  Define la posicion en x.
     * @param deamonY  Define la posicion en y.
     * @param newH  Define la altura del demonio(el rectangulo)
     * @param newW  Define la anchura del demonio(el rectangulo)
     */
    protected void settings(int deamonX, int deamonY){
        rectangle.changeColor("black");
        rectangle.movetoX(deamonX); 
        rectangle.movetoY(deamonY);
        rectangle.changeSize(10, 10);
    }
    
    /**
     * Metodo para hacer visible el demonio.
     */
    public void makeVisible(){
        rectangle.makeVisible();
    }
    
    /**
     * Metodo para hacer invisible el demonio.
     */
    public void makeInvisible(){
        rectangle.makeInvisible();
    }
    
    /**
     * Metodo para obtener la posicion en x del demonio.
     */
    public int getPositionX(){
        return rectangle.getPositionX();
    }
    
    /**
     * Metodo para obtener la posicion en y del demonio.
     */
    public int getPositionY(){
        return rectangle.getPositionY();
    }
    
    /**
     * Método para determinar si el demonio deja pasar o no las partículas a través de él.
     *
     * @param p  Partícula a evaluar.
     * @param w  Ancho del contenedor.
     */
    protected void getAccess(Particle p, int w) {
        int px = p.getPositionX();
        int py = p.getPositionY();
        int demonX = getPositionX();
        int demonY = getPositionY();
        String color = p.getTeam();
        int middle = MaxwellContainer.middle;
    
        // Verifica si la partícula está lo suficientemente cerca del demonio
        if (Math.abs(px - demonX) < 10 && Math.abs(py - demonY) < 10) {
            boolean isLeft = px < middle;
            boolean isRight = px > middle;
    
            if (isLeft && color.equals("blue")) {
                p.setX(middle + 7);  // Permitir paso a la derecha
            } else if (isRight && color.equals("red")) {
                p.setX(middle - 7);  // Permitir paso a la izquierda
            }
        }
    }

}



