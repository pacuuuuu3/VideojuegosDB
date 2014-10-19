/* Controlador para la ventana de Compania. */
package videojuegosdb.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import videojuegosdb.modelo.Compania;

/**
 * Controlador para la ventana de Compania.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class CompaniaController implements Initializable {

    private static Compania actual = null;

    /**
     * Inicializa la ventana de la Compania(descripciones de los parámetros
     * copiadas directamente de la documentación de la interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Regresa la Compania de la ventana.
     *
     * @return La Compania mostrada en ventana.
     */
    public static Compania getCompania() {
        return actual;
    }

    /**
     * Actualiza el valor para la Compania que se mostrará.
     *
     * @param c - La nueva Compania a mostrar.
     */
    public static void setCompania(Compania c) {
        actual = c;
    }

}
