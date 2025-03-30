package maxwell;


/**
 * Write a description of class Rotator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rotator extends Particle{
    public Rotator(String color, boolean isRed, int x, int y, int vx, int vy) throws MaxwellException{
        super(color, isRed, x, y, vx, vy);
    }
    
    @Override
    public void move(int dt, int width, int height) {
        for (int i = 0; i < dt; i++) {
            checkCollisions();
            x += vx;
            y += vy;
            smoothMove();
        }
    }
    
    @Override
    protected void checkCollisions() {
        if (atWallX()) {
            handleWallXCollision();
        }
        if (atWallY()) {
            vy = -vy;
            
            int aux = vx;
            vy = aux;
            vx = vy;
            y = clamp(y, 10, MaxwellContainer.h - 10);
        }
    }
    
    @Override
    protected void handleWallXCollision() {
        if (isNearMiddle() && isWrongSide()) {
            if (!centerBlock()) return;
           }
        vx = -vx;
        
        int aux = vx;
        vx = vy;
        vy = aux;
        
        
        x = clamp(x, 10, MaxwellContainer.w * 2 - 10);
    }
}
