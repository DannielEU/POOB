package maxwell;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JOptionPane;

public class MaxwellContainerTest {
    
    private MaxwellContainer container;
    private int[][] validParticles = {{50, 50, 1, 1}, {150, 50, -1, 1}};
    
    @Before
    public void setUp() {
        container = new MaxwellContainer(100, 100);
    }
    @Test
    public void testConstructorWithValidDimensions() {
        container = new MaxwellContainer(100, 100);
        assertTrue(container.ok());
    }
    @Test
    public void testAddHoleWithInvalidPosition() {
        MaxwellContainer container = new MaxwellContainer(100, 100);
        container.addHole(-1000, -1000, 2);
        assertFalse("ok() debería ser false después de añadir agujero en posición inválida", 
                   container.ok());
        // Verifica que no se añadió realmente el agujero
        assertEquals(0, container.holes().length);
    }
    // Pruebas para verificar excepciones y estado ok() = false

    @Test
    public void testAddDeamonInvalidPosition() {
        container.addDeamon(200); // Posición Y inválida (mayor que h+10)
        assertFalse(container.ok()); // ok() debe ser false
    }

    @Test
    public void testDelDemonWhenNoneExist() {
        container.delDemon(50); // Intentar eliminar demonio cuando no hay
        assertFalse(container.ok()); // ok() debe ser false
    }
    
    /* Lanza bien Exception pero no se como manejarla.
    @Test
    public void testAddParticleBeyondLimit() throws MaxwellException {
        // Crear el contenedor
        container = new MaxwellContainer(100, 100);
        // Agregar exactamente 50 partículas (límite máximo)
        for (int i = 0; i < 51; i++) {
            container.addParticle("Color" + i, true, -40, 40, 1, 1);
        }
        try {
            // Intentar agregar una partícula extra
            container.addParticle("Extra", true, 50, 50, 1, 1);
            fail("Se esperaba una MaxwellException al agregar más de 50 partículas.");
        } catch (MaxwellException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            assertEquals("There are more than 50 particles.", e.getMessage());
        }
    }
    */
   
    @Test
    public void testDelParticleWhenNoneExist() {
        container.delParticle("Rojo"); // Intentar eliminar partícula inexistente
        assertFalse(container.ok()); // ok() debe ser false
    }

    @Test
    public void testStartWithNegativeTicks() {
        int result = container.start(-10); // Ticks negativos
        assertEquals(-1, result); // Debe retornar -1
        assertFalse(container.ok()); // ok() debe ser false
    }

    // Prueba para verificar ok() = true en operaciones exitosas
    
    @Test
    public void testOkTrueAfterSuccessfulOperations() throws MaxwellException {
        container = new MaxwellContainer(100, 100, 1, 1, 1, validParticles);
        container.addHole(50, 50, 2);
        assertTrue(container.ok()); // ok() debe ser true
        
        container.delParticle(container.particles()[0][0] + "," + container.particles()[0][1]);
        assertTrue(container.ok()); // ok() debe seguir siendo true
    }

    // Prueba para verificar isGoal() no afecta ok()
    
    @Test
    public void testIsGoalDoesNotAffectOk() throws MaxwellException {
        container = new MaxwellContainer(100, 100, 0, 1, 0, new int[][]{{150, 50, -1, 0}});
        container.isGoal(); // No debería afectar ok()
        assertTrue(container.ok()); // ok() debe permanecer true
    }
    
        // ========== PRUEBAS DE CONSTRUCTOR ==========
    
    @Test
    public void testConstructorWithInvalidDimensions() {
        container = new MaxwellContainer(10, 10); 
        assertFalse(container.ok());
        // Dimensiones menores a 20
    }
    
    
    
    @Test
    public void testConstructorWithParticlesData() {
        MaxwellContainer mc = new MaxwellContainer(100, 100, 1, 1, 1, validParticles);
        assertTrue(mc.ok());
        assertEquals(1, mc.particles().length);
    }

    // ========== PRUEBAS DE DEMONIOS ==========
    
    @Test
    public void testAddValidDeamon() {
        container.addDeamon(50);
        assertTrue(container.ok());
        assertEquals(1, container.demons().length);
    }
    
    @Test
    public void testAddInvalidDeamon() {
        container.addDeamon(200); // Posición Y inválida
        assertFalse(container.ok());
    }
    
    @Test
    public void testDelExistingDeamon() {
        container.addDeamon(50);
        container.delDemon(50);
        assertTrue(container.ok());
        assertEquals(0, container.demons().length);
    }
    
    @Test
    public void testDelNonExistingDeamon() {
        container.delDemon(50);
        assertFalse(container.ok());
    }

    // ========== PRUEBAS DE PARTÍCULAS ==========
    
    @Test
    public void testAddValidParticle() throws MaxwellException {
        container.addParticle("Red", true, 50, 50, 1, 1);
        assertTrue(container.ok());
        assertEquals(1, container.particles().length);
    }
    
    @Test
    public void testDelExistingParticle() throws MaxwellException {
        container.addParticle("Red", true, 50, 50, 1, 1);
        container.delParticle("Red");
        assertTrue(container.ok());
        assertEquals(0, container.particles().length);
    }
    
    @Test
    public void testDelNonExistingParticle() {
        container.delParticle("Red");
        assertFalse(container.ok());
    }

    // ========== PRUEBAS DE AGUJEROS ==========
    
    @Test
    public void testAddValidHole() {
        container.addHole(50, 50, 2);
        assertTrue(container.ok());
        assertEquals(1, container.holes().length);
    }
    
    
    @Test
    public void testAddHoleWithEdgePosition() {
        container.addHole(1, 2, 3); // Posición en el borde
        assertTrue(container.ok());
    }

    // ========== PRUEBAS DE SIMULACIÓN ==========
    
    @Test
    public void testStartWithValidTicks() throws MaxwellException {
        MaxwellContainer mc = new MaxwellContainer(100, 100, 0, 1, 0, new int[][]{{150, 50, -1, 0}});
        int result = mc.start(100);
        assertTrue(mc.ok());
        assertTrue(result >= -1);
    }
    
    @Test
    public void testIsGoalWithSeparatedParticles() throws MaxwellException {
        MaxwellContainer mc = new MaxwellContainer(100, 100, 0, 1, 1, 
            new int[][]{{-50, 50, 0, 0}, {50, 50, 0, 0}});
        assertTrue(mc.isGoal());
    }
    
    @Test
    public void testIsGoalWithMixedParticles() throws MaxwellException {
        MaxwellContainer mc = new MaxwellContainer(100, 100, 0, 1, 1, 
            new int[][]{{70, 50, 0, 0}, {80, 50, 0, 0}});
        assertFalse(mc.isGoal());
    }

    // ========== PRUEBAS DE VISIBILIDAD ==========
    
    @Test
    public void testMakeVisible() throws MaxwellException {
        // Configuración inicial
        container.addParticle("Red", true, 50, 50, 1, 1);
        container.addDeamon(50);
        container.addHole(50, 50, 2);
        
        // Verificar que inicialmente no está visible
        assertFalse(MaxwellContainer.isVisible);
        
        // Acción
        container.makeVisible();
        
        // Verificaciones
        assertTrue(MaxwellContainer.isVisible);
        // No debería afectar el estado ok()
        assertTrue(container.ok());
    }
    
    @Test
    public void testMakeInvisible() throws MaxwellException {
        // Configuración inicial
        container.addParticle("Blue", false, 60, 60, -1, -1);
        container.makeVisible(); // Primero hacer visible
        
        // Acción
        container.makeInvisible();
        
        // Verificaciones
        assertFalse(MaxwellContainer.isVisible);
        // No debería afectar el estado ok()
        assertTrue(container.ok());
    }

    // ========== PRUEBAS DE MÉTODOS AUXILIARES ==========
    
    @Test
    public void testGenerateColorList() {
        MaxwellContainer mc = new MaxwellContainer(100, 100);
        assertTrue(mc.ok());
        // Este método es privado, pero podemos verificar sus efectos indirectos
    }
    
    @Test
    public void testUpdateParticles() throws MaxwellException {
        MaxwellContainer mc = new MaxwellContainer(100, 100, 0, 1, 0, new int[][]{{60, 50, 1, 0}});
        mc.addHole(70, 50, 1);
        // Este método es privado, pero start() lo llama
        int result = mc.start(10);
        assertTrue(mc.ok());
        assertTrue(result >= -1);
    }
}