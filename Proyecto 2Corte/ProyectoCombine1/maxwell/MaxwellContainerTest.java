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


