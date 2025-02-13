import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GraphTest{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    
     @Test
    public void shouldCreateEmptyGraph(){
        String [] vertices ={};
        String [][] edges = {};
        assertEquals(0, new Graph(vertices,edges).vertices());
        assertEquals(0, new Graph(vertices,edges).edges());
    }    
   
    @Test
    public void shouldCreateGraphs(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }    
    
    @Test
    public void shouldNotHaveDuplicateVerticesEdges(){
        String [] vertices ={"DDYA","MYSD","DOPO","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"},{"DDYA","DOPO"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }    

    @Test
    public void shouldNotBeCaseSensitive(){     
        String [] vertices ={"Ddya","MYSD","DOPO","dopo"};
        String [][] edges = {{"DDYA","Mysd"},{"ddya","dopo"},{"DDya","doPo"}};    
        assertEquals(3, new Graph(vertices,edges).vertices());
        assertEquals(2, new Graph(vertices,edges).edges());
    }
    
    @Test
    public void shouldConvertToString(){
        String [] vertices ={"DDYA","MYSD","DOPO"};
        String [][] edges = {{"DDYA","MYSD"},{"DDYA","DOPO"}};  
        String data= "(DDYA, DOPO) (DDYA, MYSD)";
        assertEquals(data, new Graph(vertices,edges).toString());
    }

    @Test
    public void shouldValityEquality(){
        String [] vertices ={};
        String [][] edges = {};
        assertEquals(new Graph(vertices,edges),new Graph(vertices,edges));
        String [] verticesA ={"DDYA","MYSD","DOPO"};
        String [][] edgesA = {{"DDYA","MYSD"},{"DDYA","DOPO"}};      
        String [] verticesB ={"Ddya","MYSD","DOPO","dopo"};
        String [][] edgesB = {{"DDYA","Mysd"},{"ddya","dopo"},{"DDya","doPo"}}; 
        assertEquals(new Graph(verticesA,edgesA),new Graph(verticesB,edgesB));
    }    


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
