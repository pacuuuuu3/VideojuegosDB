/* Clase para modelar una consola en la base de datos. */
package videojuegosdb.modelo;

import java.sql.ResultSet;

/**
 * Clase para representar una consola en la base de datos.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Consola {

    private String nombre;
    private Integer anio;

    /**
     * Constructor.
     *
     * @param nombre - El nombre de la consola.
     * @param anio - El año de salida de la consola.
     */
    public Consola(String nombre, Integer anio) {
        this.nombre = nombre;
        this.anio = anio;
    }

    /**
     * Regresa el nombre de la consola.
     *
     * @return El nombre de la consola.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Actualiza el nombre de la consola.
     *
     * @param nuevoNombre - El nuevo nombre de la consola
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    /**
     * Regresa el año de lanzamiento de la consola.
     *
     * @return El año de lanzamiento de la consola.
     */
    public Integer getAnio() {
        return this.anio;
    }

    /**
     * Actualiza el año de lanzamiento de la consola.
     *
     * @param nuevoAnio - El nuevo valor del año de lanzamiento de la consola
     */
    public void setAnio(Integer nuevoAnio) {
        this.anio = nuevoAnio;
    }

    /**
     * Regresa el id de la consola con nombre nombre.
     *
     * @param nombre - El nombre de la consola.
     * @return El id de la consola.
     */
    public static Integer getId(String nombre) {
        try {
            Integer id = null;
            ResultSet rs = Updater.search("SELECT id FROM consola WHERE "
                    + "nombre LIKE \"" + nombre + "\";");
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.err.println("Error en el método Consola.getId(String): " 
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Regresa la consola en la entrada con id = id.
     *
     * @param id - El id de la consola.
     * @return La consola con id id.
     */
    public static Consola getConsola(Integer id) {
        try {
            Consola c = null;
            String nombre;
            Integer anio;
            ResultSet rs = Updater.search("SELECT * FROM consola WHERE "
                    + "id = " + id + ";");
            if (rs.next()) {
                nombre = rs.getString("nombre");
                anio = rs.getInt("anio");
                c = new Consola(nombre, anio);
            }
            return c;
        } catch (Exception e) {
            System.err.println("Error en el método "
                    + "Consola.getConsola(Integer): " 
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

}
