import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Esta clase contiene la creacion y funcionamiento de Maxwell's Demon.
 * Versión con bajo acoplamiento
 * 
 * @author Daniel Patiño & Daniel Useche
 * @version Version 2.0
 */
public class MaxwellContainer {
    private Rectangle recta1, recta2, recta3;
    public static int w;
    public static int h;
    public static int middle;
    private ArrayList<Deamon> demons;
    private ArrayList<Particle> particles;
    private ArrayList<Hole> holes;
    private ArrayList<Rectangle> tablero;
    private static Canvas canvas;
    private int r;
    private int b;
    public static boolean isVisible;
    private boolean isOk;
    
    public MaxwellContainer(int h, int w) {
        isOk = validateDimensions(h, w);
        if (!isOk) return;
        
        this.h = h + 10;
        this.w = w + 10;
        
        canvas = new Canvas(w * 2 + 20, h + 20);
        recta1 = new Rectangle();
        recta2 = new Rectangle();
        recta3 = new Rectangle();
        tablero = new ArrayList<>();
        this.settings(h,w);
        
        demons = new ArrayList<>();
        particles = new ArrayList<>();
        holes = new ArrayList<>();
        
        isVisible = false;
    }
    
    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particlesData) {
        this(h, w);
        this.r = r;
        this.b = b;
        if(isOk){
            for (int[] p : particlesData) {
                addParticle(p[0], p[1], p[2], p[3]);
            }
            this.addDeamon(d);
        } else {
            JOptionPane.showMessageDialog(null, "Juego no Iniciado debido a un Error.");
        }
    }
    
    private void settings(int h, int w) {
        tablero.add(recta1);
        tablero.add(recta2);
        tablero.add(recta3);
        canvas.getCanvas(w * 2 + 20, h + 20);
        recta1.changeSize(h + 20, 2 * w + 20);
        recta1.changeColor("red");
        recta2.changeSize(h, 2 * w);
        recta2.changeColor("white");
        recta2.moveHorizontal(10);
        recta2.moveVertical(10);
        recta3.changeSize(h, w / 64);
        recta3.changeColor("blue");
        recta3.moveHorizontal((2 * w) / 2 - (w / 64) / 2 + 10);
        this.middle = (2 * w) / 2 - (w / 64) / 2 + 10;
        recta3.moveVertical(10);
    }
    
    private boolean validateDimensions(int h, int w) {
        if (h < 20 || w < 20) {
            JOptionPane.showMessageDialog(null, "Tamaño mínimo 20x20.");
            return false;
        } 
        if (h < (w / 40) + 10) {
            JOptionPane.showMessageDialog(null, "Juego Imposible, establezca otro tamaño.");
            return false;
        } 
        if (h > 2000 || w > 2000) {
            JOptionPane.showMessageDialog(null, "Tamaño máximo 2000x2000.");
            return false;
        }
        return true;
    }
    
    public void addDeamon(int d) {
        int demonX = this.middle;
        int demonY = h - d;
        Deamon deamon = new Deamon(demonX - 4, demonY);
        demons.add(deamon);            
    }
    
    public void delDemon(int d) {
        if (demons.isEmpty()) return;
        
        for(int i = 0; i < demons.size(); i++) {
            Deamon elDemon = demons.get(i);
            if (elDemon.getPositionY() == d) {
                elDemon.makeInvisible();
                demons.remove(i);
            }
        }
    }
    
    public void addParticle(int px, int py, int vx, int vy) {
        String color = particles.size() < r ? "red" : "blue";
        boolean isRed = color.equals("red");
        Particle p = new Particle(color, isRed, px, py, vx, vy);
        particles.add(p);       
    }
    
    public void delParticle(String color) { 
        for (Particle p : particles) {
            if(p.getColor().equals(color)) {
                p.makeInvisible();
                particles.remove(p);
                break;
            }
        }
    }
    
    public void addHole(int px, int py, int maxParticles) {
        if(px >= 0 && px <= w && py >= 0 && py <= h) {
            Hole h = new Hole(px, py, maxParticles);        
            holes.add(h);
        }
    }
    
    public void start(int ticks) {
        if(ticks <= 0) return;
        
        for (int i = 0; i < ticks; i++) {
            if (this.isGoal()) {
                JOptionPane.showMessageDialog(null, "¡Juego Terminado!");
                return;
            }
            updateParticles();
        }
    }
    
    private void updateParticles() {
        for (Particle p : particles) {
            p.move(1, recta2.getWidth(), recta2.getHeight());
            demons.forEach(d -> d.getAccess(p, recta2.getWidth()));
        }
    }
    
    public boolean isGoal() {
        for(Particle p: particles) {
            if ((!p.isRed() && p.getPositionX() <= w+5) || 
                (p.isRed() && p.getPositionX() >= w-5)) {
                return false;
            }
        }
        return true;
    }
    
    public int[][] particles() {
        int[][] data = new int[particles.size()][2];
        for (int i = 0; i < particles.size(); i++) {
            data[i][0] = particles.get(i).getPositionX();
            data[i][1] = particles.get(i).getPositionY();
        }
        return data;
    }
    
    public int[] demons() {
        int[] data = new int[demons.size()];
        for (int i = 0; i < demons.size(); i++) {
            data[i] = demons.get(i).getPositionY();
        }
        return data;
    }
    
    public int[][] holes() {
        int[][] data = new int[holes.size()][2];
        for (int i = 0; i < holes.size(); i++) {
            data[i][0] = holes.get(i).getX();
            data[i][1] = holes.get(i).getY();
        }
        return data;
    }
    
    public void makeVisible() {
        isVisible = true;
        tablero.forEach(Rectangle::makeVisible);
        particles.forEach(Particle::makeVisible);
        demons.forEach(Deamon::makeVisible);
        holes.forEach(Hole::makeVisible);
    }
    
    public void makeInvisible() {
        isVisible = false;
        tablero.forEach(Rectangle::makeInvisible);
        particles.forEach(Particle::makeInvisible);
        demons.forEach(Deamon::makeInvisible);
        holes.forEach(Hole::makeInvisible);
    }
    
    public void finish() {
        // Implementación original
    }
    
    public boolean ok() {
        return isOk;
    }
}