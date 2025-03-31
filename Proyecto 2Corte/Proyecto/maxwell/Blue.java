package maxwell;


/**
 * Write a description of class Blue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blue extends Deamon{
    public Blue(int deamonX, int deamonY){
        super(deamonX, deamonY);
    }
    
    protected void settings(int deamonX, int deamonY){
        rectangle.changeColor("blue");
        rectangle.movetoX(deamonX); 
        rectangle.movetoY(deamonY);
        rectangle.changeSize(10, 10);
    }
        
    @Override
    protected void getAccess(Particle p, int w) {
        int px = p.getPositionX();
        int py = p.getPositionY();
        int demonX = getPositionX();
        int demonY = getPositionY();
        String color = p.getTeam();
        int middle = MaxwellContainer.middle;
    
        // Verifica si la particula esta lo suficientemente cerca del demonio
        if (Math.abs(px - demonX) < 10 && Math.abs(py - demonY) < 10) {
            boolean isLeft = px < middle;
            boolean isRight = px > middle;
    
            if (isLeft && color.equals("blue")) {
                p.setX(middle + 7);  // Permitir paso a la derecha
            } else if (isRight && color.equals("blue")) {
                p.setX(middle - 7);  // Permitir paso a la izquierda
            }
        }
    }
}
