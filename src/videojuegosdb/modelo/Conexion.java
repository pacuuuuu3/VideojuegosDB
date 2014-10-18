/* Clase para la conexión a la base de datos. */
package videojuegosdb.modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase para la conexión a la base de datos.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Conexion {

    private static Connection c = null;

    /**
     * Regresa la conexión a la base de datos.
     *
     * @param base - El nombre de la base de datos.
     * @return La conexión a la base de datos.
     */
    public static Connection getConexion(String base) {
        try {
            if (c == null) {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + base);
                c.setAutoCommit(true);
            }
            return c;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
