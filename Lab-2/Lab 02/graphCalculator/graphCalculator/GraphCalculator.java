import java.util.TreeMap;

/** GraphCalculator.java
 * 
 * @author ESCUELA 2025-01
 */
    
public class GraphCalculator{
    
    private TreeMap<String,Graph> variables;
    
    public GraphCalculator(){
    }

    //Create a new variable
    public void create(String nombre){
    }
     
    //Assign a graph to an existing variable
    //a := graph
    public void assign(String graph, String[] vertices, String [][] edges ){
    }    
    
    
    //Assigns the value of a binary operation to a variable
    // a = b op v*
    //The operator characters are: '+' adding a edge between two vertices, '-' removing a edge between two vertex
    // '?' checking if a graph contains the given vertices
    // 'p' return the graph with a path that passes through all the vertices in the indicated order
    public void assignUnary(String a, String b, char op, String [] vertices){
    }
      
    
    //Assigns the value of a binary operation to a variable
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, 'd' difference, 'j' join
    public void assignBinary(String a, String b, char op, String c){
    }
  
    
    //Returns the graph with the edges in uppercase in alphabetical order.
    public String toString(String graph){
        return null;
    }
    
    
    //If the last operation was successfully completed
    public boolean ok(){
        return false;
    }
}
    



