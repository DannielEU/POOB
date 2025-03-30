package maxwell;

public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {80, 45, 2, 0},
            {-25, 12, 1, 3},
        };

        // Crear instancia de MaxwellContest
        MaxwellContest contest = new MaxwellContest();
        
        // Parámetros de la simulación
        int width = 200;
        int height = 200;
        int demons = 20;
        int redParticles = 1;
        int blueParticles = 1;
        
        // Opción 1: Solo resolver (más rápido)
        boolean isPossible = contest.solve(width, height, demons, redParticles, blueParticles, particlesData);
        System.out.println("¿Es posible resolver? " + isPossible);
        
        // Opción 2: Simulación completa con visualización
        contest.simulate(height, width, demons, blueParticles, redParticles, particlesData);
        
        // También puedes agregar agujeros si es necesario
        // Nota: Necesitarías exponer este método en MaxwellContest o hacerlo antes
        // MaxwellContainer container = new MaxwellContainer(width, height, demons, blueParticles, redParticles, particlesData);
        // container.addHole(35, 50, 2);
    }
}

