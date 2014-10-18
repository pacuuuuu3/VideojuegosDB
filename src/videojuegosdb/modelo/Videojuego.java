/* Clase para abstraer un videojuego en la base de datos. */
package videojuegosdb.modelo;

import java.sql.ResultSet;

/**
 * Abstrae la información de un videojuego.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Videojuego {

    private String nombre;

    /**
     * Constructor.
     *
     * @param nombre - El nombre del videojuego.
     */
    public Videojuego(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el nombre del juego.
     *
     * @return El nombre del juego.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Actualiza el nombre del juego.
     *
     * @param nuevoNombre - El nuevo nombre del juego.
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    /**
     * Regresa el nombre del juego cuyo id en la base es id.
     *
     * @param id - El id del juego en la base.
     * @return El nombre del juego con el id dado.
     */
    public static String getNombre(Integer id) {
        try {
            String nombre = null;
            ResultSet rs = Updater.search("SELECT nombre FROM videojuego WHERE "
                    + "id = " + id + ";");
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
            return nombre;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Regresa el id del juego cuyo nombre en la base es nombre.
     *
     * @param nombre - El nombre del juego en la base.
     * @return El id del juego con el nombre dado.
     */
    public static Integer getId(String nombre) {
        try {
            Integer id = null;
            ResultSet rs = Updater.search("SELECT id FROM videojuego WHERE "
                    + "nombre LIKE \"" + nombre + "\";");
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Agrega el juego a la base de datos.
     */
    public void agregaJuego() {
        String agrega = "INSERT INTO videojuego (nombre) VALUES (\""
                + this.nombre + "\");";
        Updater.update(agrega);
    }

    /**
     * Elimina el juego de la base de datos.
     */
    public void remove() {

    }
}
