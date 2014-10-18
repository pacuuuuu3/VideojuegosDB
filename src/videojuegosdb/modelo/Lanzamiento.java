/* Clase para modelar los elementos de la tabla salio_para. */
package videojuegosdb.modelo;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase para modelar elementos de la tabla salio_para de la base de datos.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Lanzamiento {

    private String videojuego;
    private String consola;
    private String compania;
    private String desarrollador;
    private Integer anio;
    private String clasificacion;
    private Integer calificacion;

    /**
     * Constructor vacío
     */
    public Lanzamiento() {
    }

    /**
     * Constructor.
     *
     * @param videojuego - El nombre del videojuego
     * @param consola - La consola para la que salió el videojuego.
     * @param compania - La compañía que publicó el videojuego.
     * @param desarrollador - El desarrollador del videojuego.
     * @param anio - El año de salida del videojuego.
     * @param clasificacion - La clasificación del videojuego según la ESRB.
     * @param calificacion - La calificación del videojuego.
     */
    public Lanzamiento(String videojuego, String consola, String compania,
            String desarrollador, Integer anio, String clasificacion,
            Integer calificacion) {
        this.videojuego = videojuego;
        this.consola = consola;
        this.compania = compania;
        this.desarrollador = desarrollador;
        this.anio = anio;
        this.clasificacion = clasificacion;
        this.calificacion = calificacion;
    }

    /**
     * Regresa el nombre del videojuego.
     *
     * @return El nombre del videojuego.
     */
    public String getVideojuego() {
        return this.videojuego;
    }

    /**
     * Actualiza el nombre del juego.
     *
     * @param nuevoJuego - El nuevo nombre para el juego.
     */
    public void setVideojuego(String nuevoJuego) {
        this.videojuego = nuevoJuego;
    }

    /**
     * Regresa el nombre de la consola para la que salió el juego.
     *
     * @return El nombre de la consola para la que salió el juego.
     */
    public String getConsola() {
        return this.consola;
    }

    /**
     * Actualiza el valor de la consola.
     *
     * @param nuevaConsola - El nuevo valor de la consola.
     */
    public void setConsola(String nuevaConsola) {
        this.consola = nuevaConsola;
    }

    /**
     * Regresa el nombre de la compañía que publicó el juego.
     *
     * @return El nombre de la compañía que publicó el juego.
     */
    public String getCompania() {
        return this.compania;
    }

    /**
     * Actualiza el valor de la compañía que publicó el juego.
     *
     * @param nuevaCompania - El nuevo valor para la compañía.
     */
    public void setCompania(String nuevaCompania) {
        this.compania = nuevaCompania;
    }

    /**
     * Regresa el desarrollador del juego.
     *
     * @return La compañía que desarrolló el juego.
     */
    public String getDesarrollador() {
        return this.desarrollador;
    }

    /**
     * Actualiza el valor del desarrollador.
     *
     * @param nuevoDesarrollador - El nuevo valor para el desarrollador.
     */
    public void setDesarrollador(String nuevoDesarrollador) {
        this.desarrollador = nuevoDesarrollador;
    }

    /**
     * Regresa el año de lanzamiento del juego.
     *
     * @return El año de lanzamiento del juego.
     */
    public Integer getAnio() {
        return this.anio;
    }

    /**
     * Actualiza el valor del año de lanzamiento.
     *
     * @param nuevoAnio - El valor para el año de lanzamiento.
     */
    public void setAnio(Integer nuevoAnio) {
        this.anio = nuevoAnio;
    }

    /**
     * Regresa la clasificación del juego según la ESRB.
     *
     * @return La clasificación del juego según la ESRB.
     */
    public String getClasificacion() {
        return this.clasificacion;
    }

    /**
     * Actualiza el valor de la clasificación del juego.
     *
     * @param nueva - El nuevo valor para la clasificación del juego.
     */
    public void setClasificacion(String nueva) {
        this.clasificacion = nueva;
    }

    /**
     * Regresa la calificación del juego.
     *
     * @return La calificación del juego.
     */
    public Integer getCalificacion() {
        return this.calificacion;
    }

    /**
     * Actualiza el valor de la calificación del juego.
     *
     * @param nuevoScore - El nuevo valor para la calificación del juego.
     */
    public void setCalificacion(Integer nuevoScore) {
        this.calificacion = nuevoScore;
    }

    /**
     * Regresa una lista de Lanzamientos construídos a partir de un ResultSet
     * obtenido de una consulta de SQLite.
     *
     * @param resultados - Un ResultSet formado por lanzamientos.
     * @return Una Lista con los lanzamientos del ResultSet.
     */
    public static List<Lanzamiento> getLanzamientos(ResultSet resultados) {
        try {
            List<Lanzamiento> regreso = new LinkedList<>();
            Lanzamiento current;
            Integer comp, dev, calif;
            List<Integer> idJuego, idConsola, idCompania, idDesarrollador,
                    year, score;
            idJuego = new LinkedList<>();
            idConsola = new LinkedList<>();
            idCompania = new LinkedList<>();
            idDesarrollador = new LinkedList<>();
            year = new LinkedList<>();
            score = new LinkedList<>();
            List<String> clasificacion = new LinkedList<>();
            while (resultados.next()) {
                idJuego.add(resultados.getInt("id_juego"));
                idConsola.add(resultados.getInt("id_consola"));
                idCompania.add(resultados.getInt("id_compania"));
                idDesarrollador.add(resultados.getInt("id_desarrollador"));
                year.add(resultados.getInt("anio"));
                score.add(resultados.getInt("calificacion"));
                clasificacion.add(resultados.getString("clasificacion"));
            }
            Iterator<Integer> itJuego, itConsola, itCompania, itDesarrollador,
                    itYear, itScore;
            itJuego = idJuego.iterator();
            itConsola = idConsola.iterator();
            itCompania = idCompania.iterator();
            itDesarrollador = idDesarrollador.iterator();
            itYear = year.iterator();
            itScore = score.iterator();
            for (String c : clasificacion) {
                current = new Lanzamiento();
                current.setVideojuego(Videojuego.getNombre(itJuego.next()));
                current.setConsola(Consola.getConsola(itConsola.next()).getNombre());
                comp = itCompania.next();
                if (comp != 0) {
                    current.setCompania(Compania.getCompania(comp).getNombre());
                }
                dev = itDesarrollador.next();
                if (dev != 0) {
                    current.setDesarrollador(Compania.getCompania(dev).getNombre());
                }
                current.setAnio(itYear.next());
                calif = itScore.next();
                if (calif != 0) {
                    current.setCalificacion(calif);
                }
                current.setClasificacion(c);
                regreso.add(current);
            }
            return regreso;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

}
