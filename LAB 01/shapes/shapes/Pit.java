import java.util.ArrayList;

/**
 * A pit are a set of squares and rectangles that can be manipulated and that draws itself on a canvas.
 * 
 * @authors  Daniel Useche & Daniel Patiño
 * @version 1.0  (February 2025)
 */

class Pit {
    private Rectangle rect1, rect2;
    private ArrayList<Rectangle> seeds;
    private int x, y;
    private String pitColor, seedColor;
    private boolean isBig;
    /**
     * In this phase, we create and define the squares, their color, size(1 big and 1 that is the half of the size of the big) and the array that will contains the seeds.
     * We also define color of each square and seeds color (This will help us to distinguish the teams).
     */  
    public Pit(boolean isBig, boolean isNorth) {
        rect1 = new Rectangle();
        rect2 = new Rectangle();
        seeds = new ArrayList<>();
        this.isBig = isBig;

        pitColor = isNorth ? "red" : "blue";
        seedColor = isNorth ? "yellow" : "green"; // Color fijo de semillas por equipo.

        if (isBig) {
            rect1.changeSize(195, 100);
            rect2.changeSize(185, 90);
        } else {
            rect1.changeSize(100, 100);
            rect2.changeSize(90, 90);
        }

        rect1.changeColor("black");
        rect2.changeColor(pitColor);
    }
        /**
     * This method will help us to put n seeds inside of each square.
     */
    public void putSeeds(int count) {
        for (int i = 0; i < count; i++) {
            putSeed(seedColor); // Usar el color original del pozo
        }
    }
    /**
     * This method will help us to save the color of the eliminated seed in a array 
     */
    public ArrayList<String> removeSeedsAndGetColors() {
        ArrayList<String> removedColors = new ArrayList<>();
        while (!seeds.isEmpty()) {
            Rectangle seed = seeds.remove(seeds.size() - 1);
            removedColors.add(seed.getColor());
            seed.makeInvisible();
        }
        return removedColors;
    }
        /**
     * This method will create the seed setting the color, size, visibility and position of each one
     */
    public void putSeed(String color) {
        Rectangle seed = new Rectangle();
        seed.changeColor(color);
        seed.changeSize(15, 15);
        seeds.add(seed);
        seed.makeVisible();
        updateSeedPositions();
    }
        /**
     * This method will remove n seeds of the square.
     */ 
    public void removeSeeds(int count) {
        for (int i = 0; i < count && !seeds.isEmpty(); i++) {
            Rectangle seed = seeds.remove(seeds.size() - 1);
            seed.makeInvisible();
        }
    }
    /**
     * This method will return the size of the array.
     */
    public int seeds() {
        return seeds.size();
    }
    /**
     * This method will return the initial color of the seeds.
     */
    public String getOriginalSeedColor() {
        return seedColor;
    }
    /**
     * This method will move the squares to the desired position.
     */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        rect1.setxPosition(x);
        rect1.setyPosition(y);
        rect2.setxPosition(x + 5);
        rect2.setyPosition(y + 5);
        updateSeedPositions();
    }
    /*
     * This method will update the last seed position, this is useful when you add and delete seeds from squares, this method save the last seed position.
     */
    private void updateSeedPositions() {
        int seedX = x + 10, seedY = y + 10;
        int spacing = 20, cols = isBig ? 4 : 4; // Mayor capacidad en almacenes
        int j = 0, k = 0;
        for (Rectangle seed : seeds) {
            seed.setxPosition(seedX + j * spacing);
            seed.setyPosition(seedY + k * spacing);
            j++;
            if (j >= cols) {
                j = 0;
                k++;
            }
        }
    }
    /**
     * This method will make visible the squares and seeds.
     */
    public void makeVisible() {
        rect1.makeVisible();
        rect2.makeVisible();
        for (Rectangle seed : seeds) {
            seed.makeVisible();
        }
    }
    /**
     * This method will delete the current position of the seeds, create new seeds and make sure their positions are correct.
     */
    public void setSeeds(int n) {
        seeds.clear();  // Elimina las semillas actuales
        for (int i = 0; i < n; i++) {
            Rectangle seed = new Rectangle();
            seed.changeColor(seedColor); // Usa el color original de la semilla
            seed.changeSize(15, 15);
            seeds.add(seed);
        }
        updateSeedPositions(); // Asegurar que las semillas están bien ubicadas
    }
    /**
     * This method will make invisible the squares and seeds.
     */
    public void makeInvisible() {
        rect1.makeInvisible();
        rect2.makeInvisible();
        for (Rectangle seed : seeds) {
            seed.makeInvisible();
        }
    }
    
}





