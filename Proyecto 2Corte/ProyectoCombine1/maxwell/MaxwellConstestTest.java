package maxwell;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The test class MaxwellConstestTest.
 *
 * @author  Daniel Patiño & Daniel Useche
 * @version Version 1.1
 */
public class MaxwellConstestTest
{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){
        
    }
    
    @Test
    public void test1(){
        int w = 7;
        int h = 4;
        double d = 1;
        int r = 1;
        int b = 1;
        double[][] particles1 = {
            {2, 1, 4, 1}, 
            {-3, 1, 2, 0} 
        };
        
        String resultado = MaxwellContest.solve(w,h,d, r, b, particles1);
        
        assertEquals("24,0",resultado);
        
    }
    
    @Test
     public void test2(){
        int w = 4;
        int h = 4;
        int d = 1;
        int r = 2;
        int b = 2;
        double[][] particles2 = {
            {3, 1, 2, 2},   
            {-2, 3, -2, -1}, 
            {3, 2, 1, -2},  
            {-2, 2, 2, 2}  
        };
            
        String resultado = MaxwellContest.solve(w, h, d, r, b, particles2);
        assertEquals("impossible", resultado);
        
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
