/* Clase para modelar una consola en la base de datos. */
package videojuegosdb.modelo;

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
     * @param nombre - El nombre de la consola.
     * @param anio - El año de salida de la consola.
     */
    public Consola(String nombre, Integer anio){
        this.nombre = nombre;
        this.anio = anio;
    }
    
    /**
     * Regresa el nombre de la consola.
     * @return El nombre de la consola.
     */
    public String getNombre(){
        return this.nombre;
    }
    
}
