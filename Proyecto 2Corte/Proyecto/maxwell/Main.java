package maxwell;
public class Main {
    public static void main(String[] args) throws MaxwellException {
        int[][] particlesData = {
            { 15, 10, 1, -2}
            ,{ 35, 10, -3, 2},
            { 15, 10, 2, -2}
            ,{ -65, 16, -3, 1},
            { 15, 10, 2, -2}
            ,{ -265, 16, -3, 1}
        };

        MaxwellContainer container = new MaxwellContainer(150, 150, 75, 1, 2, particlesData);
        container.addParticle("normal", "Green", true, 30, 60, -3, 3);
        container.addParticle("ephemeral", "White", false, 20, 20, 6, -4);
        container.addParticle("rotator", "Yellow",  false, 110, 50, 2, 5); 
        //public MaxwellContainer           (int h, int w, int d, int b, int r, int[][] particlesData)
        container.makeVisible();
        container.start(10000);
        
    }
}

