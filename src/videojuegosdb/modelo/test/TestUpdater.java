/* Clase para probar la clase Updater. */
package videojuegosdb.modelo.test;

import org.junit.Assert;
import org.junit.Test;
import videojuegosdb.modelo.*;

/**
 * Clase para probar la clase {@link Updater}
 *
 * @author Víctor Zamora Gutiérrez
 * @versin 1.0
 */
public class TestUpdater {

    /**
     * Prueba unitaria para el método Search.
     */
    @Test
    public void testSearch() {
        try {
            Conexion.setNombreDB("test.db");
            Videojuego v = new Videojuego("Cosa");
            v.agregaJuego();
            Assert.assertTrue(Updater.search("SELECT * FROM videojuego;").next());
            v.remove();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

/**
 * Prueba unitaria para el método Update.
 */
@Test
    public void testUpdate(){
}
    
}
