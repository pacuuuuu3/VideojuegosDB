/* Clase que modela una compañía en la base de datos. */
package videojuegosdb.modelo;

import java.sql.ResultSet;

/**
 * Clase que modela una compañía en la base de datos.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Compania {

    private String nombre;
    private Integer anioFundacion;

    /**
     * Constructor.
     *
     * @param nombre - El nombre de la compañía.
     * @param anio - El año de fundación de la compañía.
     */
    public Compania(String nombre, Integer anio) {
        this.nombre = nombre;
        this.anioFundacion = anio;
    }

    /**
     * Regresa el nombre de la compañía.
     *
     * @return El nombre de la compañía.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Actualiza el nombre de la compañía.
     *
     * @param nuevoNombre - El nuevo nombre de la compañía.
     */
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    /**
     * Regresa el año de fundación de la compañía.
     *
     * @return El año de fundación de la compañía.
     */
    public Integer getAnioFundacion() {
        return this.anioFundacion;
    }

    /**
     * Actualiza el año de fundación de la compañía.
     *
     * @param nuevoAnio - El nuevo valor del año de fundación de la compañía.
     */
    public void setAnioFundacion(Integer nuevoAnio) {
        this.anioFundacion = nuevoAnio;
    }

    /**
     * Regresa el id de la compañia cuyo nombre es nombre.
     *
     * @param nombre - El nombre de la compañía.
     * @return El id de la compañía en la base de datos.
     */
    public static Integer getId(String nombre) {
        try {
            Integer id = null;
            ResultSet rs = Updater.search("videojuegos.db",
                    "SELECT id FROM compania WHERE "
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
    
    

}
