package maxwell;


/**
 * Write a description of class Red here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Red extends Hole{
    public Red(int x, int y, int maxParticles) throws MaxwellException{
        super(x, y, maxParticles);
        
        if (isOutOfBounds()) {
            throw new MaxwellException(MaxwellException.OUTOFRANGE + " " + this);
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
    
    public boolean tryAbsorb(Particle p) {
        String color = p.getTeam();
        
        if (!canAbsorb()) {
            this.makeInvisible();
            return false;
            }
        double distance = Math.sqrt(Math.pow(p.getPositionX() - x, 2) + 
                          Math.pow(p.getPositionY() - y, 2));
        int particleRadius = 5;
        int holeRadius = 5;
        
        if(p.getClass() == Flying.class){
            return false;
        }
        
        if (distance < (particleRadius + holeRadius) && color.equals("red")){
            absorbedParticleColors.add(p.getColor());
            return true;
        }
        
        return false;
    }
    
}
