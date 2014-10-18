/* Clase de pruebas unitarias para la conexión a la base de datos. */
package videojuegosdb.modelo.test;

import java.sql.Connection;
import org.junit.Assert;
import org.junit.Test;
import videojuegosdb.modelo.*;

/**
 * Clase para probar la clase {@link Conexion}
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class TestConexion {

    /**
     * Prueba que la conexión se obtenga correctamente.
     */
    @Test
    public void testGetConexion() {
        try {
            Conexion.setNombreDB("test.db");
            Connection c = Conexion.getConexion();
            Assert.assertTrue(c.isValid(0));
            c.close();
        } catch (Exception e) {
           System.err.println("Error en el método "
                   + "TestConexion.testGetConexion(): " 
                   + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
