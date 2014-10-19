/* Clase para modelar una consola en la base de datos. */
package videojuegosdb.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
     * Regresa la compañía que publicó la consola.
     *
     * @return El nombre de la compañía que publicó la consola.
     * @throws java.sql.SQLException Si falla la conexión con la base de datos.
     */
    public String getCompania() throws SQLException {
        String regreso = null;
        Integer id = getId(this.nombre);
        ResultSet companias = Updater.search("SELECT id_compania FROM "
                + "publico_consola WHERE id_consola = " + id + ";");
        if (companias.next()) {
            id = companias.getInt("id_compania");
            regreso = Compania.getCompania(id).getNombre();
        }
        return regreso;
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

    /**
     * Elimina la consola de la base de datos.
     *
     * @throws java.sql.SQLException Si falla la conexión a la base de datos.
     */
    public void remove() throws SQLException {
        Integer conId = Consola.getId(this.nombre);
        ResultSet lanzamientosConsola = Updater.search("SELECT * FROM "
                + "salio_para WHERE id_consola = " + conId + ";");
        List<Lanzamiento> aEliminar = Lanzamiento.getLanzamientos(lanzamientosConsola);
        for (Lanzamiento l : aEliminar) {
            l.remove();
        }
        Updater.update("DELETE FROM consola WHERE id = " + conId + ";");
        Updater.update("DELETE FROM publico_consola WHERE id_consola = "
                + conId + ";");
    }

}
