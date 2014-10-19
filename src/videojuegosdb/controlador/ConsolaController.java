/* Controlador para interfaz de Consolas. */
package videojuegosdb.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import videojuegosdb.modelo.Consola;

/**
 * Controlador para la ventana de Consola.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class ConsolaController implements Initializable {

    private static Consola actual = null;

    /**
     * Inicializa la ventana de la Consola(descripciones de los parámetros
     * copiadas directamente de la documentación de la interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Regresa la Consola actual.
     *
     * @return La Consola mostrada en la ventana.
     */
    public static Consola getConsola() {
        return actual;
    }

    /**
     * Actualiza el valor de la Consola.
     *
     * @param nueva - La nueva Consola a mostrar.
     */
    public static void setConsola(Consola nueva) {
        actual = nueva;
    }

}
