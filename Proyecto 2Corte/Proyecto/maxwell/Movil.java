package maxwell;
import shapes.*;

/**
 * Write a description of class Movil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movil extends Hole{
    public int vx ; 
    public int vy;
    
    /**
     * Constructor for objects of class Movil
     */
    public Movil(int x, int y, int maxParticles) throws MaxwellException{
        super(x, y, maxParticles);
        this.vx = 1;
        this.vy = 1;
        
        if (isOutOfBounds()) {
            throw new MaxwellException(MaxwellException.OUTOFRANGE + " " + this);
        }
        setupCircle(10);
        makeVisible();
    }
    
    private boolean isOutOfBounds() {
        return x < 11 || x > MaxwellContainer.w * 2 + 10 || y < 11 || y > MaxwellContainer.h + 10;
    }
    
    private void setupCircle(int diameter) {
        circle.changeSize(diameter);
        circle.changeColor("black");
        circle.movetoX(x);
        circle.movetoY(y);
    }
    
    protected void move(int dt, int width, int height) {
        for (int i = 0; i < dt; i++) {
            checkCollisions();
            x += vx;
            y += vy;
            smoothMove();
        }
    }
    
    protected void smoothMove() {
        int newX = limitStep(circle.getX(), x, 5);
        int newY = limitStep(circle.getY(), y, 5);
        
        updateCircle(newX, newY);
    }
    
    private int limitStep(int current, int target, int step) {
        return Math.abs(current - target) > step ? current + Integer.signum(target - current) * step : target;
    }
    
    protected void checkCollisions() {
        if (atWallX()) {
            handleWallXCollision();
        }
        if (atWallY()) {
            vy = -vy;
            y = clamp(y, 10, MaxwellContainer.h - 10);
        }
    }
    
    protected void handleWallXCollision() {
        if (isNearMiddle()) {
            if (!centerBlock()) return;
           }
        vx = -vx;
        x = clamp(x, 10, MaxwellContainer.w * 2 - 10);
    }
    
    protected boolean isNearMiddle() {
        return x >= MaxwellContainer.middle - 2 && x <= MaxwellContainer.middle + 2;
    }
    
    protected boolean centerBlock() {
        return Math.abs(x - MaxwellContainer.middle) <= 9;
    }
    
    protected boolean atWallX() {
        return x <= 10 || x >= MaxwellContainer.w * 2 - 11 || isNearMiddle();
    }
    
    protected boolean atWallY() {
        return y <= 10 || y >= MaxwellContainer.h - 10;
    }
    
    protected int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    private void updateCircle() {
        updateCircle(x, y);
    }
    
    private void updateCircle(int newX, int newY) {
        if (MaxwellContainer.isVisible) {
            circle.makeInvisible();
        }
        circle.movetoX(newX);
        circle.movetoY(newY);
        if (MaxwellContainer.isVisible) {
            circle.makeVisible();
        }
    }
        
}
