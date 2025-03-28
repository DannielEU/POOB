package maxwell;
import shapes.*;

/**
 * Esta clase contiene la creacion y caracteristicas de las particulas.
 * Versión mejorada con colisiones precisas y movimiento suave
 * 
 * @author Daniel Patiño & Daniel Useche
 * @version Version 1.5
 */
public class Particle {
    private int x, y;
    private int vx, vy;
    private boolean isRed;
    private Circle circle;
    
    public Particle(String color, boolean isRed, int x, int y, int vx, int vy) {
        this.x = x + MaxwellContainer.w;
        this.y = MaxwellContainer.h - y;
        this.vx = vx;
        this.vy = vy;
        this.isRed = isRed;
        this.circle = new Circle();
        this.settings(this.x, this.y, 8);  // Usamos las coordenadas ya ajustadas
    }
    
    private void settings(int x, int y, int diameter) {
        this.circle.changeSize(diameter);
        this.circle.movetoX(x);
        this.circle.movetoY(y);
        this.circle.changeColor(isRed ? "red" : "blue");
    }
    
    public void move(int dt, int width, int height) {
        for(int i = 0; i < dt; i++) {
            this.crash(width, height);
            x += vx;
            y += vy;
            this.softMove(width, height);
        }
    }
    
    public void softMove(int w, int h) {
        int targetX = x;
        int targetY = y;
        
        // Movimiento suave con límite de 5px por frame
        if(Math.abs(circle.getX() - targetX) > 5) {
            targetX = circle.getX() + (targetX > circle.getX() ? 5 : -5);
        }
        if(Math.abs(circle.getY() - targetY) > 5) {
            targetY = circle.getY() + (targetY > circle.getY() ? 5 : -5);
        }
        
        if(MaxwellContainer.isVisible) {
            circle.makeInvisible();
        }
        circle.movetoX(targetX);
        circle.movetoY(targetY);
        if(MaxwellContainer.isVisible) {
            circle.makeVisible();
        }
    }
    
    public void crash(int width, int height) {
        boolean crashedX = atWallX();
        boolean crashedY = atWallY();
        
        if(crashedY) {
            this.vy = -vy;
            atWallYDown();
            atWallYUp();
        }
        
        if(crashedX) {
            // Caso especial para pared central
            if(x > MaxwellContainer.middle - 2 && x < MaxwellContainer.middle + 2) {
                boolean onWrongSide = (isRed && x > MaxwellContainer.middle) || 
                                     (!isRed && x < MaxwellContainer.middle);
                if(onWrongSide && !centerBlock()) {
                    // Demonio permite pasar - no rebotar
                } else {
                    vx = -vx;
                    // Ajuste para evitar quedarse pegado
                    if(x > MaxwellContainer.middle) {
                        x = MaxwellContainer.middle + 3;
                    } else {
                        x = MaxwellContainer.middle - 3;
                    }
                }
            } else {
                vx = -vx;
            }
            atWallXDown();
            atWallXUp();
        }
    }
    
    public boolean centerBlock() {
        int centerX = MaxwellContainer.middle;
        boolean nearDemon = Math.abs(x - centerX) <= 9;
        return nearDemon;
    }
    
    public boolean atWallX() {
        if(x <= 10 || x >= MaxwellContainer.w * 2 - 11) {
            return true;
        }
        
        // Detección mejorada para pared central
        int middle = MaxwellContainer.middle;
        return (x >= middle - 2 && x <= middle + 2) || 
               (vx > 0 && x + vx > middle && x < middle) ||
               (vx < 0 && x + vx < middle && x > middle);
    }
    
    public boolean atWallY() {
        return y <= 10 || y >= MaxwellContainer.h - 10;
    }
    
    public void atWallXDown() {
        if(x < 10) {
            x = 10;
            if(vx < 0) vx = -vx;
            updateCircle(x, y);
        }
    }
    
    public void atWallXUp() {
        if(x > MaxwellContainer.w * 2 - 10) {
            x = MaxwellContainer.w * 2 - 10;
            if(vx > 0) vx = -vx;
            updateCircle(x, y);
        }
    }
    
    public void atWallYDown() {
        if(y < 10) {
            y = 10;
            if(vy < 0) vy = -vy;
            updateCircle(x, y);
        }
    }
    
    public void atWallYUp() {
        if(y > MaxwellContainer.h - 10) {
            y = MaxwellContainer.h - 10;
            if(vy > 0) vy = -vy;
            updateCircle(x, y);
        }
    }
    
    private void updateCircle(int newX, int newY) {
        if(MaxwellContainer.isVisible) {
            circle.makeInvisible();
        }
        circle.movetoX(newX);
        circle.movetoY(newY);
        if(MaxwellContainer.isVisible) {
            circle.makeVisible();
        }
    }
    
    // Resto de métodos se mantienen exactamente iguales
    public boolean wheremI() {
        return x > MaxwellContainer.middle;
    }
    
    @Override
    public String toString() {
        return String.format("Particle(x=%d, y=%d, vx=%d, vy=%d)", x, y, vx, vy);
    }
    
    public int getPositionX() {
        return x;
    }
    
    public int getPositionY() {
        return y;
    }
    
    public void setXPosition(int x) {
        this.x = x;
    }
    
    public String getColor() {
        return isRed ? "red" : "blue";
    }
    
    public void makeVisible() {
        circle.makeVisible();       
    }
    
    public boolean isRed() {
        return isRed;
    }
    
    public void makeInvisible() {
        circle.makeInvisible();
    }
}
