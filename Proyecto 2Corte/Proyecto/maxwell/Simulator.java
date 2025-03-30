package maxwell;

public class Simulator{
    public static void Simulator() throws MaxwellException {
            int[][] particlesData = {
                { 15, 10, 1, -2}
                ,{ 35, 10, -3, 2},
                { 15, 10, 2, -2}
                ,{ -65, 16, -3, 1},
            };
    
            MaxwellContainer container = new MaxwellContainer(150, 150, 75, 1, 2, particlesData);
            container.addParticle("normal", "Green", true, 30, 60, -3, 3);
            container.addParticle("ephemeral", "White", false, 20, 20, 6, -4);
            container.addParticle("rotator", "Yellow",  false, 110, 50, 2, 5); 
            container.addParticle("flying", "Cyan", true, -65, 120, 2, 2);
            container.addHole(-20, 100, 2);
            //public MaxwellContainer           (int h, int w, int d, int b, int r, int[][] particlesData)
            container.makeVisible();
            container.start(10000);
            
        }
}
