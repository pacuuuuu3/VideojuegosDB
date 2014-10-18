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
    private static String nombreDB = "videojuegos.db";

    /**
     * Regresa la conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     */
    public static Connection getConexion() {
        try {
            if (c == null) {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + nombreDB);
                c.setAutoCommit(true);
            }
            return c;
        } catch (Exception e) {
            System.err.println("Error en el método Conexion.getConexion(): " 
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Establece el nombre de la base de datos.
     *
     * @param nombre - El nombre de la base de datos.
     */
    public static void setNombreDB(String nombre) {
        nombreDB = nombre;
    }
}
