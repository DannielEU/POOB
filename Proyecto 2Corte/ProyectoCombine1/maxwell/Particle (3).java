package maxwell;

import shapes.*;

/**
 * Representa una partícula con movimiento y colisiones optimizadas.
 * 
 * @author Daniel Patiño & Daniel Useche
 * @version 1.6 (Optimizada)
 */
public class Particle {
    protected int x, y, vx, vy;
    protected boolean isRed;
    private Circle circle;
    protected String color;
    
    public Particle(String color, boolean isRed, int x, int y, int vx, int vy) throws MaxwellException {
        this.x = x + MaxwellContainer.w;
        this.y = MaxwellContainer.h - y;
        this.vx = -vx;
        this.vy = -vy;
        this.isRed = isRed;
        this.circle = new Circle();
        this.color = color;
        
        if (isOutOfBounds()) {
            throw new MaxwellException(MaxwellException.OUTOFRANGE + " " + this);
        }
        setupCircle(8);
    }
    
    private boolean isOutOfBounds() {
        return x < 11 || x > MaxwellContainer.w * 2 + 10 || y < 11 || y > MaxwellContainer.h + 10;
    }
    
    private void setupCircle(int diameter) {
        circle.changeSize(diameter);
        circle.changeColor(isRed ? "red" : "blue");
        updateCircle();
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
        if (isNearMiddle() && isWrongSide()) {
            if (!centerBlock()) return;
           }
        vx = -vx;
        x = clamp(x, 10, MaxwellContainer.w * 2 - 10);
    }
    
    protected boolean isNearMiddle() {
        return x >= MaxwellContainer.middle - 2 && x <= MaxwellContainer.middle + 2;
    }
    
    protected boolean isWrongSide() {
        return (isRed && x > MaxwellContainer.middle) || (!isRed && x < MaxwellContainer.middle);
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
        
    public void setX(int x) {
        this.x = x;
        updateCircle(x, this.y);
    }
    
    public void setY(int y) {
        this.y = y;
        updateCircle(this.x, y);
    }

    @Override
    public String toString() {
        return String.format("Particle(x=%d, y=%d, vx=%d, vy=%d)", x - MaxwellContainer.w, MaxwellContainer.h - y, -vx, -vy);
    }
    
    public int getPositionX() {
        return x;
    }
    
    public int getPositionY() {
        return y;
    }
    
    public int getVx(){
        return vx;
    }
    
    public int getVy(){
        return vy;
    }
    
    public boolean isRed() {
        return isRed;
    }
    public String getColor(){
        return this.color;
    }
    public String getTeam(){
        if (isRed){return "red";}else{return "blue";}
    }
    public void makeVisible() {
        circle.makeVisible();
    }
    
    public void makeInvisible() {
        circle.makeInvisible();
    }
}
