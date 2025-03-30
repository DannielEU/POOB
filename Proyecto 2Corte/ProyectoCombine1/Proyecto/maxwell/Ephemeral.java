package maxwell;


/**
 * Write a description of class Ephemeral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ephemeral extends Particle{
    /**
     * Constructor de Ephemeral
     */
    public Ephemeral(String color, boolean isRed, int x, int y, int vx, int vy) throws MaxwellException{
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
            if(vy > 0) vy -= 1;
            else if(vy < 0) vy +=1;
            
            y = clamp(y, 10, MaxwellContainer.h - 10);
        }
    }
    
    @Override
    protected void handleWallXCollision() {
        if (isNearMiddle() && isWrongSide()) {
            if (!centerBlock()) return;
           }
        vx = -vx;
        if(vx > 0) vx -= 1;
        else if(vx < 0) vx +=1;
        x = clamp(x, 10, MaxwellContainer.w * 2 - 10);
    }
}
