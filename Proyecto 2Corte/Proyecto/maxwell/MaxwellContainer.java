package maxwell;
import shapes.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.Arrays;

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
        try{
            if(h <= 40 || w <= 40) throw new MaxwellException(MaxwellException.DIMENSIONESERROR);
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
        }catch(MaxwellException e){
            if(!isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
            System.exit(1);
        }
    }
    
    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particlesData) {
        this(h, w);

        this.r = r;
        this.b = b;
        int ref = 0;
        for (int[] p : particlesData) {
            try{
                String color = this.generateColorList().get(ref);
                ref++;
                boolean isRed = particles.size() < r;
                addParticle(color,isRed,p[0], p[1], p[2], p[3]);
            }catch(MaxwellException e){
                if(isVisible){
                    JOptionPane.showMessageDialog(null, e);
                }
                isOk = false;
            }
        }
        this.addDeamon(d);
    }
    private List<String> generateColorList() {
        return new ArrayList<>(Arrays.asList(
            "Red", "Blue", "Green", "Yellow", "Orange", "Purple", "Cyan", "Magenta", "Pink", "Brown",
            "Lime", "Teal", "Lavender", "Turquoise", "Beige", "Maroon", "Olive", "Coral", "Navy", "Gold",
            "Silver", "Peach", "Violet", "Indigo", "Charcoal", "Ivory", "Salmon", "Azure", "Mint", "Tan",
            "Plum", "Ruby", "Amber", "Emerald", "Sapphire", "Rose", "Pearl", "Aqua", "Fuchsia", "Khaki",
            "Burgundy", "Moss", "Mustard", "Lilac", "Periwinkle", "Crimson", "Opal", "Sienna", "Graphite", "Copper"
        ));
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
    
    public void addDeamon(String type, int d ){
        try{
            int demonX = this.middle;
            int demonY = h - d;
            Deamon dm;
            if(demonY < 10 || demonY > h+10) throw new MaxwellException(MaxwellException.DEAMONINVALID);
            
            if(type.toLowerCase() == "blue") {
                dm = new Blue(demonX - 4, demonY);   
                demons.add(dm);
            }
            if(type.toLowerCase() == "weak"){
                dm = new Weak(demonX - 4, demonY, this);
                demons.add(dm);
            }
            isOk = true;
        } catch(MaxwellException e){
            if(isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
        }
    }
    
    public void addDeamon(int d) {
        try{
            int demonX = this.middle;
            int demonY = h - d;
            if(demonY < 10 || demonY > h+10) throw new MaxwellException(MaxwellException.DEAMONINVALID);
            Deamon deamon = new Deamon(demonX - 4, demonY);
            demons.add(deamon);  
            isOk = true;
        }catch(MaxwellException e){
            if(isVisible){
                    JOptionPane.showMessageDialog(null, e);
                }
            isOk = false;
        }          
    }
    
    public void delDemon(int d) {
        try{
            if(particles.size() == 0) throw new MaxwellException(MaxwellException.NOTEXISTDEMON);
            for (int i = demons.size() - 1; i >= 0; i--) {
                Deamon elDemon = demons.get(i);
                if (elDemon.getPositionY() == h-d) {
                    elDemon.makeInvisible();
                    demons.remove(i);
                }
            }
        }catch(MaxwellException e){
            if(isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
        }
    }

    public void addParticle(String color, boolean isRed, int px, int py, int vx, int vy) throws MaxwellException {
        if(particles.size() == 50) throw new MaxwellException(MaxwellException.PARTICLEINVALID);
        Particle p = new Particle(color, isRed, px, py, vx, vy);
        particles.add(p);       
    }
    
    public void addParticle(String type, String color, boolean isRed, int px, int py, int vx, int vy) throws MaxwellException{
        Particle p;
        try{
            if(type.toLowerCase() == "normal"){
                p = new Particle(color, isRed, px, py, vx, vy);
                particles.add(p);
            } else if(type.toLowerCase() == "ephemeral"){
                p = new Ephemeral(color, isRed, px, py, vx, vy);
                particles.add(p);
            } else if(type.toLowerCase() == "flying"){
                p = new Flying(color, isRed, px, py, vx, vy);
                particles.add(p);
            } else if(type.toLowerCase() == "rotator"){
                p = new Rotator(color, isRed, px, py, vx, vy);
                particles.add(p);
            }
        } catch(MaxwellException e){
            JOptionPane.showMessageDialog(null, e);
            isOk = false;
        }
    }
    
    public void delParticle(String color) { 
        try{
            if(particles.size() == 0) throw new MaxwellException(MaxwellException.NOTEXISTPARTICLE);
            Iterator<Particle> i = particles.iterator();
            while(i.hasNext()){
                Particle p = i.next();
                if(p.getColor().equals(color)){
                    p.makeInvisible();
                    i.remove();
                }
                
                if(p.getVx() == 0 && p.getVy() == 0){
                    p.makeInvisible();
                    i.remove();
                }
            }
        }catch(MaxwellException e){
            if(isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
        }
    }
    
    public void addHole(int px, int py, int maxParticles)  {
        try{
            Hole h = new Hole(px, py, maxParticles);        
            holes.add(h);
        }catch(MaxwellException e){
            if(isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
        }
    }
    
    public void addHole(String type, int px, int py, int maxParticles){
        Hole h;
        try{
            if(type.toLowerCase() == "normal"){
                h = new Hole(px, py, maxParticles);
                holes.add(h); 
            }
            if(type.toLowerCase() == "movil"){
                h = new Movil(px, py, maxParticles);
                holes.add(h);
            }
            if(type.toLowerCase() == "red"){
                h = new Red(px, py, maxParticles);
                holes.add(h);
            }
            
        }catch(MaxwellException e){
            if(isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
        }
        
    }
    
    public int start(int ticks) {
        try{
            if(ticks <= 0) throw new MaxwellException(MaxwellException.TIMENEGATIVE);
            if(isVisible){
                JOptionPane.showMessageDialog(null, "¡Juego Iniciado!");
            }
            for (int i = 0; i < ticks; i++) {
                if (this.isGoal()) {
                    return -1;
                }
                isOk = true;
                updateParticles();
            }
            return -1;
        }catch(MaxwellException e){
            if(isVisible){
                JOptionPane.showMessageDialog(null, e);
            }
            isOk = false;
            return -1;
        }
    }
    
    private void updateParticles() { 
        ArrayList<String> particlesToRemove = new ArrayList<>();
        
        Iterator<Particle> i = particles.iterator();
        while(i.hasNext()){
            Particle p = i.next();
            p.move(1, recta2.getWidth(), recta2.getHeight());
            
            for (Hole h : holes) { 
                if (h.tryAbsorb(p) && h.canAbsorb()) {
                    particlesToRemove.add(p.getColor());
                    break; 
                }
            }
            
            if(!particlesToRemove.contains(p.getColor())){
                demons.forEach(d -> d.getAccess(p, recta2.getWidth()));
            }
            
            if(p.getVx() == 0 && p.getVy() == 0){
                p.makeInvisible();
                i.remove();
            }
        }
        
        for (String color : particlesToRemove) {
            this.delParticle(color); 
        }
    }
    
    public boolean isGoal() {
        for(Particle p: particles) {
            if ((!p.isRed() && p.getPositionX() <= w+5) || 
                (p.isRed() && p.getPositionX() >= w-5)) {
                return false;
            }
        }
        if(isVisible){
            JOptionPane.showMessageDialog(null, "Juego terminado!");
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
        System.exit(0);
        particles.clear();
        demons.clear();
        holes.clear();
    }
    
    public boolean ok() {
        return isOk;
    }
}