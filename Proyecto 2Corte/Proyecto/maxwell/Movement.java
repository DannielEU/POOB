package maxwell;


/**
 * Write a description of interface Movement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface Movement{
    public void move(int dt, int width, int height);
    
    public void smoothMove();
    
    public void checkCollisions() ;
    
    public void handleWallXCollision() ;
    
    public boolean isNearMiddle();
    
    public boolean centerBlock() ;
    
    public boolean atWallX();
    
    public boolean atWallY() ;
    
    public int clamp(int value, int min, int max) ;
    
    public void setX(int x);
    
    public void setY(int y);
    
    public int getPositionX();
    
    public int getPositionY();
    
    public int getVx();
    
    public int getVy();
    
}
