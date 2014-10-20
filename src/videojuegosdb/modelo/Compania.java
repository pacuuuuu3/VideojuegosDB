/* Clase que modela una compañía en la base de datos. */
package videojuegosdb.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
        if (anio != 0) {
            this.anioFundacion = anio;
        }
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
            ResultSet rs = Updater.search("SELECT id FROM compania WHERE "
                    + "nombre LIKE \"" + nombre + "\";");
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            System.err.println("Error en el método Compania.getId(String): "
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Regresa una entrada de la tabla como un objeto de tipo Compania.
     *
     * @param id - El id en la tabla de la entrada.
     * @return La compañía con el id dado.
     */
    public static Compania getCompania(Integer id) {
        try {
            Compania c = null;
            String nombre;
            Integer anioFundacion;
            ResultSet rs = Updater.search("SELECT * FROM compania WHERE "
                    + "id = " + id + ";");
            if (rs.next()) {
                nombre = rs.getString("nombre");
                anioFundacion = rs.getInt("anio_fundacion");
                c = new Compania(nombre, anioFundacion);
            }
            return c;
        } catch (Exception e) {
            System.err.println("Error en el método "
                    + "Compania.getCompania(Integer): "
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * Elimina la compañía de la base de datos.
     *
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    public void remove() throws SQLException {
        Integer id = Compania.getId(this.nombre);
        Updater.update("DELETE FROM publico_consola WHERE id_compania = " + id
                + ";");
        Updater.update("DELETE FROM compania WHERE id = " + id + ";");
    }

    /**
     * Regresa una lista con los nombres de todas las compañías.
     *
     * @return Una lista con los nombres de todas la compañías.
     */
    public static List<String> getAll() throws SQLException {
        List<String> s = new LinkedList<>();
        ResultSet nombres = Updater.search("SELECT nombre FROM compania;");
        while (nombres.next()) {
            s.add(nombres.getString("nombre"));
        }
        return s;
    }

    public static List<Compania> getCompanias(ResultSet resultados, String tabla) {
        try {
            List<Compania> regreso = new LinkedList<>();
            if (tabla.equals("salio_para")) {
                List<Integer> id = new LinkedList<>();
                while (resultados.next()) {
                    id.add(resultados.getInt("id_compania"));
                }
                for (Integer i : id) {
                    regreso.add(Compania.getCompania(i));
                }
            } else if (tabla.equals("compania")) {
                while (resultados.next()) {
                    regreso.add(new Compania(resultados.getString("nombre"), resultados.getInt("anio_fundacion")));
                }
            }
            return regreso;
        } catch (Exception e) {
            System.err.println("Error en el método "
                    + "Compania.getCompanias(ResultSet): "
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
