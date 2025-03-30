package maxwell;
import java.util.*;

/**
 * Esta clase contiene la simulacion y solucion al problema de Maxwell's Demon.
 * 
 * @author Daniel Patiño & Daniel Useche
 * @version Version 1.1
 */
public class MaxwellContest{
     /**
     * 
     * @param  h   define la altura del juego.
     * @param  w   define el ancho del juego.
     * @param  d   define la cantidad de demonios en el juego.
     * @param  b   define la cantidad de particulas azules.
     * @param  r   define la cantidad de partiucilas rojas.
     * @param  particles   define las caracteristicas de cada particula como posicion y velocidad.
     * @return  el tiempo minimo en el que las particulas estan organizadas.
     */
    
    public MaxwellContest() {
        
    }
    public boolean solve(int w, int h, int d, int r, int b, int [][] particles){
        MaxwellContainer container = new MaxwellContainer(w,h,d,r,b,particles);
        int minTime = container.start(10000000);
        return (minTime > 0);
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
        if(this.solve(w,h,d,r,b,particles)){
            MaxwellContainer container = new MaxwellContainer(w,h,d,r,b,particles);
            MaxwellContainer containerSolve = new MaxwellContainer(w,h,d,r,b,particles);
            int minTime = containerSolve.start(1000000);
            System.out.println("tiempo minimo: "+ minTime);
            container.makeVisible();
            container.start(minTime);
        }else{
            System.out.println("Impossible");
        }
    }
}
