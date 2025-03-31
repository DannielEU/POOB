package maxwell;
/**
 * Write a description of class Weak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weak extends Deamon{ //Revisar, error en el funcionamiento
    private MaxwellContainer container;
    
    public Weak(int deamonX, int deamonY, MaxwellContainer container){
        super(deamonX, deamonY);
        this.container = container;
    }
    
    protected void settings(int deamonX, int deamonY){
        rectangle.changeColor("green");
        rectangle.movetoX(deamonX); 
        rectangle.movetoY(deamonY);
        rectangle.changeSize(10, 10);
    }
    
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
    
            if (isLeft && color.equals("blue")){
                p.setX(middle + 7);  // Permitir paso a la derecha
                this.makeInvisible();
                container.delDemon(demonY);
            } else if (isRight && color.equals("red")) {
                p.setX(middle - 7);  // Permitir paso a la izquierda
                this.makeInvisible();
                container.delDemon(demonY);
                }
        }
    }
}


