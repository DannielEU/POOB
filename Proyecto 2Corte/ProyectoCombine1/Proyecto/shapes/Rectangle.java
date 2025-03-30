package shapes;
import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle extends Figure{

    public static int EDGES = 4;
    private int height;
    private int width;

    /**
     * Create a new rectangle at default position with default color.
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public Rectangle(int x, int y, int Height, int Width, String Color){
        height = Height;
        width = Width;
        xPosition = x;
        yPosition = y;
        color = Color;
        isVisible = false;
    }
    
    public Rectangle(){
        height = 30;
        width = 40;
        xPosition = 0;
        yPosition = 0;
        color = "magenta";
        isVisible = false;
    }
    

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /*
     * Draw the rectangle with current specifications on screen.
     */
    @Override
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }
    
    public int getPositionX(){
        return xPosition;
    }
    
    public int getPositionY(){
        return yPosition;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}

