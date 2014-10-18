/* Clase para modelar los elementos de la tabla salio_para. */
package videojuegosdb.modelo;

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
}
