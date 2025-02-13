import java.awt.*;
import java.lang.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    
    public final int VERTICES=3;
    
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        height = 30;
        width = 40;
        xPosition = 140;
        yPosition = 15;
        color = "green";
        isVisible = false;
    }

    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the triangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the triangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the triangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the triangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
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
     * Slowly move the triangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
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
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * 
     * Desarrollamos el metodo area
     */
    public void area(){
        int area;
        area= (height*width)/2;
        System.out.println(area);
    }
    
    
    /**
     * 
     * Desarrollamos el metodo equilateral()
     */
    private double sideEquilateral(){
        int areaequilateral;
        areaequilateral = (height*width)/2;
        
        double lado;
        lado=Math.sqrt(4*areaequilateral/(Math.sqrt(3)));
        
        return lado;
          
    }
    
    public void equilateral(){
        int newWidth = (int) sideEquilateral();
        int newHeight = (int) (Math.sqrt(3)* newWidth)/2;

        changeSize(newHeight, newWidth);
        
        
    }
    
    /**
     * Creacion de metodo shrink
     */
    
    public void shrink(int times, int limitHeight){
        if (limitHeight<0){
            limitHeight=0;
        }
        double timesReduction= (height-limitHeight)/times;
        
        for(int i=0; i< times; i++){
         int newHeight= (int) (height - timesReduction);
         changeSize(newHeight, width);         
        }
    }
    
    /**
      * Creacion de metodo para crear tirangulo en una posicion especifica;
      */
    public void trianglePosition(int newxPosition, int newyPosition){
        Triangle newTriangle = new Triangle();
        newTriangle.xPosition = newxPosition;
        newTriangle.yPosition = newyPosition;
        newTriangle.makeVisible();
    }
    
    
    
    /**
     * Creacion de metodo shrinkWidth
     */
    public void shrinkWitdth(int times, int limitWidth){
        if (limitWidth<0){
            limitWidth=0;
        }
        double timesReduction= (width-limitWidth)/times;
        
        for(int i=0; i< times; i++){
         int NewWidth= (int) (width - timesReduction);
         changeSize(height, NewWidth);         
        }
    }
    
}
