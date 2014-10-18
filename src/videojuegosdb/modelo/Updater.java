/* Clase para realizar operaciones de SQL. */
package videojuegosdb.modelo;

import java.sql.*;

/**
 * Clase para realizar operaciones de SQL.
 *
 * @author Víctor Zamora Gutierrez
 * @version 1.0
 */
public class Updater {

    private static Statement stmt = null;

    /**
     * Ejecuta una consulta en la base de datos.
     *
     * @param query - La consulta a realizar.
     * @return Un ResultSet con los resultados de la consulta.
     */
    public static ResultSet search(String query) {
        try {
            initializeStatement();
            ResultSet regreso = stmt.executeQuery(query);
            return regreso;
        } catch (Exception e) {
            System.err.println("Error en el método Updater.search(String): " 
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Actualiza la base de datos.
     *
     * @param update - La actualización a realizar.
     */
    public static void update(String update) {
        try {
            initializeStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.err.println("Error en el método Updater.update(String): " 
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /* Inicializa el Statement de la clase. */
    private static void initializeStatement() {
        try {
            if (stmt == null) {
                Connection c = Conexion.getConexion();
                stmt = c.createStatement();
            }
        } catch (Exception e) {
            System.err.println("Error en el método "
                    + "Updater.initializeStatement(): " 
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Regresa el Statement de la clase.
     *
     * @return El Statement de la clase.
     */
    public static Statement getStatement() {
        return stmt;
    }

}
