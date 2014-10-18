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
     * @param database - El nombre de la base de datos.
     * @param query - La consulta a realizar.
     * @return Un ResultSet con los resultados de la consulta.
     */
    public static ResultSet search(String database, String query) {
        try {
            initializeStatement(database);
            ResultSet regreso = stmt.executeQuery(query);
            return regreso;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Actualiza la base de datos.
     *
     * @param database - El nombre de la base de datos.
     * @param update - La actualización a realizar.
     */
    public static void update(String database, String update) {
        try {
            initializeStatement(database);
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /* Inicializa el Statement de la clase. */
    private static void initializeStatement(String database) {
        try {
            if (stmt == null) {
                Connection c = Conexion.getConexion(database);
                stmt = c.createStatement();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
