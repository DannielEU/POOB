import java.util.ArrayList;

class Pit {
    private Rectangle rect1, rect2;
    private ArrayList<Rectangle> seeds;
    private int x, y;
    private String pitColor, seedColor;
    private boolean isBig;

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
    public void putSeeds(int count) {
        for (int i = 0; i < count; i++) {
            putSeed(seedColor); // Usar el color original del pozo
        }
    }
    public ArrayList<String> removeSeedsAndGetColors() {
        ArrayList<String> removedColors = new ArrayList<>();
        while (!seeds.isEmpty()) {
            Rectangle seed = seeds.remove(seeds.size() - 1);
            removedColors.add(seed.getColor()); // Obtener el color antes de eliminar
            seed.makeInvisible();
        }
        return removedColors;
    }




    public void putSeed(String color) {
        Rectangle seed = new Rectangle();
        seed.changeColor(color);
        seed.changeSize(15, 15);
        seeds.add(seed);
        seed.makeVisible();
        updateSeedPositions();
    }

    public void removeSeeds(int count) {
        for (int i = 0; i < count && !seeds.isEmpty(); i++) {
            Rectangle seed = seeds.remove(seeds.size() - 1);
            seed.makeInvisible();
        }
    }

    public int seeds() {
        return seeds.size();
    }

    public String getOriginalSeedColor() {
        return seedColor;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
        rect1.setxPosition(x);
        rect1.setyPosition(y);
        rect2.setxPosition(x + 5);
        rect2.setyPosition(y + 5);
        updateSeedPositions();
    }

    private void updateSeedPositions() {
        int seedX = x + 10, seedY = y + 10;
        int spacing = 20, cols = isBig ? 5 : 4; // Mayor capacidad en almacenes
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

    public void makeVisible() {
        rect1.makeVisible();
        rect2.makeVisible();
        for (Rectangle seed : seeds) {
            seed.makeVisible();
        }
    }

    public void makeInvisible() {
        rect1.makeInvisible();
        rect2.makeInvisible();
        for (Rectangle seed : seeds) {
            seed.makeInvisible();
        }
    }
}





