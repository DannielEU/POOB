import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    private int diameter;
    
    public Circle(int x, int y, int d, String Color){
        diameter = d;
        xPosition = x;
        yPosition = y;
        color = Color;
        isVisible = false;
    }   
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    protected void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    protected void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    protected void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    protected void moveLeft(){
        moveHorizontal(-20);
    }
    protected void movetoY(int y){
        yPosition = y;
    }
    protected void movetoX(int x){
        xPosition = x;
    }
    /**
     * Move the rectangle a few pixels up.
     */
    protected void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    protected void moveDown(){
        moveVertical(20);
    }

    
    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    protected void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    protected void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
    
    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    protected void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    protected void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    protected void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Erase the rectangle on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    public int getRadius(){
        return diameter/2;
    }
    public Circle(){
        diameter = 30;
        xPosition = 0;
        yPosition = 0;
        color = "blue";
        isVisible = false;
    }

    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }
    public int getX(){
        return xPosition;
    }
    public int getY(){
        return yPosition;
    }
}
