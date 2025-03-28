package shapes;
import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle extends Figure{
    
    public static int VERTICES=3;
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
    @Override
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

}
