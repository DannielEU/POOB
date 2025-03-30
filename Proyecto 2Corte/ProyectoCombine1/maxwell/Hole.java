package maxwell;
import shapes.*;
import java.util.*;
public class Hole {
    private Circle circle;
    private int x;
    private int y;
    private int maxParticles;
    private ArrayList<String> absorbedParticleColors;
    
    public Hole(int x, int y, int maxParticles) throws MaxwellException {
        this.x = x + MaxwellContainer.w;
        this.y = MaxwellContainer.h - y;
        this.maxParticles = maxParticles;
        this.absorbedParticleColors = new ArrayList<>();
        this.circle = new Circle();
        if (isOutOfBounds()) {
            throw new MaxwellException(MaxwellException.OUTOFRANGE + " " + this.toString());
        }
        setupCircle(10);
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
    
    public void makeVisible() {
        circle.makeVisible();
    }
    
    public void makeInvisible() {
        circle.makeInvisible();
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return String.format("Particle(x=%d, y=%d, vx=%d, vy=%d)", x - MaxwellContainer.w, MaxwellContainer.h - y);
    }
    
    public boolean canAbsorb() {
        return absorbedParticleColors.size() < maxParticles;
    }
    
    public boolean tryAbsorb(Particle p) {
        if (!canAbsorb()) {
            this.makeInvisible();
        }
        double distance = Math.sqrt(Math.pow(p.getPositionX() - x, 2) + 
                          Math.pow(p.getPositionY() - y, 2));
        int particleRadius = 5;
        int holeRadius = 5;
        
        if (distance < (particleRadius + holeRadius)) {
            absorbedParticleColors.add(p.getColor());
            return true;
        }
        return false;
    }
    
    public ArrayList<String> getAbsorbedParticleColors() {
        return new ArrayList<>(absorbedParticleColors);
    }
}