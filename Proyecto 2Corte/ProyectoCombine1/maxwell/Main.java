package maxwell;
public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            { 175, 10, 2, -2}
            ,{ 35, 10, -3, 2},
            { -34, 10, 2, -2}
            ,{ -65, 16, -3, 1},
            { 15, 10, 2, -2}
            ,{ -53, 16, -3, 1}
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 2, 2, particlesData);
        //public MaxwellContainer           (int h, int w, int d, int b, int r, int[][] particlesData
        
        container.addHole(35,50,2);
        System.out.println(container.start(10000)/60);// de milisegundos a segundos
        
    }
}

