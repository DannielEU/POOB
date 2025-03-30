package shapes;
import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Figure{

    public static final double PI=3.1416;
    private int diameter;
    
    public Circle(int x, int y, int d, String Color){
        diameter = d;
        xPosition = x;
        yPosition = y;
        color = Color;
        isVisible = false;
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

    @Override
    public void draw(){
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
