import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    
    public static int VERTICES=3;
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    private int height;
    private int width;
    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(int x, int y, int Height, int Width, String Color){
        height = Height;
        width = Width;
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
    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        height = 30;
        width = 40;
        xPosition = 0;
        yPosition = 0;
        color = "green";
        isVisible = false;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    /*
     * Draw the triangle with current specifications on screen.
     */

    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

}
