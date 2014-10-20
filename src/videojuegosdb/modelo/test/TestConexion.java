/* Clase de pruebas unitarias para la conexión a la base de datos. */
package videojuegosdb.modelo.test;

import java.sql.Connection;
import java.sql.SQLException;
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
     *
     * @throws java.sql.SQLException Si falla la conexión a la base de datos.
     */
    @Test
    public void testGetConexion() throws SQLException {
        Conexion.setNombreDB("test.db");
        Connection c = Conexion.getConexion();
        Assert.assertTrue(c.isValid(100));
        c.close();
    }
}
