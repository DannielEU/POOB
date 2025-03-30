package maxwell;
import shapes.*;
/**
 * Esta clase contiene la creacion y caracteristicas del agujero.
 * 
 * @author Daniel Patiño & Daniel Useche
 * @version Version 1.1
 */
    
public class Hole {
    private Circle circle;
    private int x, y;
    private int maxParticles;
    private int absortionRadius = 5;
    
    public Hole(int x, int y, int maxParticles) {
        this.x = x;
        this.y = y;
        this.maxParticles = maxParticles;
        circle = new Circle();
        this.settings(y,x);
    }
    
    /*
     *Metodo que define las caracteristicas del agujero.
     *
     *@param y  Define la posicion en x del agujero.
     *@param x  Define la posicion en y del agujero.
     */
    private void settings(int y, int x){
        circle.changeColor("black");
        circle.moveHorizontal(x);
        circle.moveVertical(y);
        circle.changeSize(5); 
    }
    
    /**
     * Metodo para hacer visible el agujero.
     */
    public void makeVisible(){
        circle.makeVisible();
    }
    
    /**
     * Metodo para hacer invisible el agujero.
     */
    public void makeInvisible(){
        circle.makeInvisible();
    }
    
    /**
     * Metodo para obtener la posicion en x el agujero.
     */
    public int getX(){
        return x;
    }
    
    /**
     * Metodo para obtener la posicion en y el agujero.
     */
    public int getY(){
        return y;
    }
}

