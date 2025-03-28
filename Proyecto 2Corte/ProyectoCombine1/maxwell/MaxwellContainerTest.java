package maxwell;
import javax.swing.JOptionPane;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MaxwellContainerTest.
 *
 * @author  Daniel Patiño & Daniel Useche
 * @version 1.1
 */
public class MaxwellContainerTest
{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){       
    }
    
    MaxwellContainer container = new MaxwellContainer(200, 200, 0, 50, 50, new int[0][0]); 
        
    @Test
    public void accordingPUshouldAddDemon(){
        container.addDeamon(90);
        int[] demons = container.demons();
        assertEquals(1, demons.length);
        assertEquals(90, demons[0]);
    }
    
    @Test
    public void accordingPUshouldNotAddDemon(){
        container.addDeamon(-200);
        int[] demons = container.demons();
        assertEquals(0, demons.length); 
    }
    
    @Test
    public void accordingPUshoulddelDemon(){
        container.addDeamon(15);
        assertTrue(container.ok());
        container.delDemon(15);
        int [] demons = container.demons();
        assertEquals(0, demons.length);  
    }
    
    @Test
    public void accordingPUshouldNotdelDemon(){
        container.addDeamon(20);
        assertTrue(container.ok());
        container.delDemon(201);
        int[] demons = container.demons();
        assertEquals(1, demons.length);
    }
    
    /*
    @Test
    public void accordinPUshouldNotDelParticle(){ //Revisar
        container.addParticle("blue", false,100,100,1,1);
        container.delParticle("green");
        assertFalse(container.hasParticles("green")); 
    }
    */
    

    @Test
    public void accordingPUshouldAddHole(){
        container.addHole(20,20,1);
        assertTrue(container.ok());
        int[][] holes = container.holes();
        assertEquals(1, holes.length);
        assertEquals(20, holes[0][0]);
        assertEquals(20, holes[0][1]);
    }
    
    @Test
    public void accordingPUshouldNotAddHole(){
        container.addHole(-10000,10,1);
        assertTrue(container.ok());
        int[][] holes = container.holes();
        assertEquals(0, holes.length);
    }
    
    @Test
    public void accordingPUshouldStart(){
        container.start(10);
        assertTrue(container.ok());
        
        int [][] particles = container.particles();
        for (int[] particle : particles){
            assertNotEquals(10, particles[0]);    
        }
    }
    
    @Test
    public void accordingPUshouldNotStart(){
        container.start(10);
        assertTrue(container.ok());
        int [][] particles = container.particles();
        for (int[] particle : particles){
            assertEquals(10, particles[0]);    
        }
    }
    
    //Revisar
    /*
    @Test
    public void accordingPUshouldIsGoal(){        

        int[][] particlesData = {
            {1, -15, 10, 2, -4},
            {1, 223, 124, -9, 7},
            {1, 32,110, -12, 8},
            {0, 100, 40, 10, 10},
            {0, 200, 120, -20, 10},
            {0, 150, 25, 20, 10},
            
            
        };
        MaxwellContainer containerGoal = new MaxwellContainer(200, 200, 100, 3, 3, particlesData);
        assertTrue(container.ok());
        container.addDeamon(100);
        container.start(500);
        assertFalse(containerGoal.isGoal());
        
    }
    */
    
    
    //Revisar
    /*
    @Test
    public void accordingPUshouldNotIsGoal(){        

        int[][] particlesData = {
            {1, 50, 10, 0, 0},
            {1, 223, 124, 0, 7},
            {1, 32,110, 0, 8},
            {0, 100, 40, 0, 10},
            {0, 200, 120, 0, 10},
            {0, 150, 25, 0, 10 },
            
            
        };
        MaxwellContainer containerGoal = new MaxwellContainer(200, 200, 100, 3, 3, particlesData);
        assertTrue(container.ok());
        container.addDeamon(100);
        container.start(500);
        assertTrue(containerGoal.isGoal());
        
    }
    */
   
       // Pruebas para el método finish
    @Test
    public void shouldNotFinishIfVisible() {
        MaxwellContainer container = new MaxwellContainer(100, 100);
        container.makeVisible();
        container.finish();
        assertTrue(container.ok());
    }
    
    @Test
    public void shouldNotFinishIfNotVisible() {
        MaxwellContainer container = new MaxwellContainer(100, 100);
        container.finish();
        assertTrue(container.ok());
    }
   
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown(){
        
    }   
}


