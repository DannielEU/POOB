package maxwell;

import java.util.*;

/**
 * Esta clase contiene la simulacion y solucion al problema de Maxwell's Demon.
 * 
 * @author Daniel Patiño & Daniel Useche & IA
 * @version Version 1.1
 */
public class MaxwellContest{
    static final double EPS = 1e-9; //Compara valores con presicion muy pequeña
    static final int maxIterations = 10000; // Iteraciones máximas para buscar la solucion mas optima
    
    
    // Función para reflejar la posición en el eje y en un intervalo [0, h]
    private static double reflect(double pos, double height) { 
        double mod = pos % (2 * height);
        if (mod < 0) mod += 2 * height;
        if (mod <= height + EPS) return mod;
        else return 2 * height - mod;
    }

    // Para una particula que necesita cambiar de lado, retorna el mínimo tiempo t >= 0 tal que:
    // t = (2*w*k - px) / vx   para algún entero k 
    
    // Encuentra el menor tiempo en el que una particula cruza al otro lado.
    private static double findMinTime(double px, double py, double vx, double vy, double w, double h, double d) {
        double best = Double.POSITIVE_INFINITY; // Si no se encuentra ningún tiempo valido en maxIterations se retorna infinito
        
        // Si la particula no se mueve en x nunca cruzara
        if (vx > 0) { 
            int kStart = (int) Math.ceil(px / (2 * w)); // Math.ceil redondea hacia arriba
            for (int k = kStart; k < kStart + maxIterations; k++) {
                double t = (2 * w * k - px) / vx;
                if (t < 0) continue;
                // Verificar la condicion en y
                if (Math.abs(vy) < EPS) {
                    // Si vy es 0, la posicion y es constante
                    if (Math.abs(py - d) < EPS) {
                        best = Math.min(best, t);
                        break; // se encontro el menor candidato
                    }
                } else {
                    double y = reflect(py + vy * t, h);
                    if (Math.abs(y - d) < EPS) {
                        best = Math.min(best, t);
                        break; // al aumentar k, t aumenta, por lo que se puede salir
                    }
                }
            }
        } else { 
            // Para vx < 0, se requiere: 2*w*k - px <= 0
            int kStart = (int) Math.floor(px / (2 * w));
            for (int k = kStart; k > kStart - maxIterations; k--) {
                double t = (2 * w * k - px) / vx; 
                if (t < 0) continue;
                if (Math.abs(vy) < EPS) {
                    if (Math.abs(py - d) < EPS) {
                        best = Math.min(best, t);
                        break;
                    }
                } else {
                    double y = reflect(py + vy * t, h);
                    if (Math.abs(y - d) < EPS) {
                        best = Math.min(best, t);
                        break;
                    }
                }
            }
        }
        return best;
    }
    
    /**
     * El metodo solve resuelve el juego con los parametros deseados
     * 
     * @param  h   define la altura del juego.
     * @param  w   define el ancho del juego.
     * @param  d   define la cantidad de demonios en el juego.
     * @param  b   define la cantidad de particulas azules.
     * @param  r   define la cantidad de partiucilas rojas.
     * @param  particles   define las caracteristicas de cada particula como posicion y velocidad.
     * @return  el tiempo minimo en el que las particulas estan organizadas.
     */
    
    public static String solve(int w, int h, double d, int r, int b, double[][] particles) {
        int total = r + b;

        // Se calcula para cada partícula que deba cambiar de lado, el minimo tiempo en el que puede hacerlo.
        // Las particulas rojas quedaran a la izquierda y las azules a la derecha
        double overallTime = 0;
        boolean possible = true;
        for (int i = 0; i < total; i++) {
            double px = particles[i][0];
            double py = particles[i][1];
            double vx = particles[i][2];
            double vy = particles[i][3];
            // Determinar en que lado inicia la particula
            boolean inLeft = (px < 0);
            boolean needFlip = false;
            
            if (i < r) { //Particulas rojas
                if (!inLeft) needFlip = true;
            } else { // Particulas azules
                if (inLeft) needFlip = true;
            }
            if (needFlip) {
                double minTime = findMinTime(px, py, vx, vy, w, h, d);
                if (minTime == Double.POSITIVE_INFINITY) {
                    possible = false;
                } else {
                    overallTime = Math.max(overallTime, minTime);
                }
            }
            // Las particulas ya correctamente ubicadas no imponen restriccion en el tiempo.
        }
        if (!possible) {
            return "impossible";
        } else {
            return String.format("%.1f", overallTime);
        }
    }

    /**
     * El metodo simulate simula MaxwellContainer con los parametros deseados
     * 
     * @param  h   define la altura del juego.
     * @param  w   define el anch   o del juego.
     * @param  d   define la cantidad de demonios en el juego.
     * @param  b   define la cantidad de particulas azules.
     * @param  r   define la cantidad de partiucilas rojas.
     * @param  particles   define las caracteristicas de cada particula como posicion y velocidad.
     */
    public void simulate(int h, int w, int d, int b, int r, int[][] particles){
        MaxwellContainer container = new MaxwellContainer(h, w, d, b, r, particles);
        container.start(1000);
    }
}
