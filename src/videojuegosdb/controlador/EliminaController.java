/* Controlador para la ventana de eliminar. */
package videojuegosdb.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Controlador para la ventana de eliminar.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class EliminaController implements Initializable {

    @FXML
    private Label name;

    private static String nombre;
    private static String clase;

    /**
     * Inicializa la ventana de eliminar con el nombre del objeto a eliminar
     * (descripciones de los parámetros copiadas directamente de la
     * documentación de la interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(nombre);
    }

    /**
     * Actualiza el nombre del objeto a eliminar.
     *
     * @param nuevoNombre - El nuevo nombre del objeto a eliminar.
     */
    public static void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    /* Elimina el objeto a eliminar. */
    private void remove() {
        switch (clase) {
            case "Lanzamiento":
                LanzamientoController.getLanzamiento().remove();
                break;
            case "Compania":
                CompaniaController.getCompania().remove();
                break;
            case "Consola":
                ConsolaController.getConsola().remove();
        }
    }
    
    /**
     * Manejador para el botón Sí.
     */
    protected void handleBotonSi(){
       remove();
    }

    /**
     * Actualiza el nombre de la clase del objeto a eliminar.
     *
     * @param nuevaClase - El nuevo nombre de la clase del objeto a eliminar.
     */
    public static void setClase(String nuevaClase) {
        clase = nuevaClase;
    }
}
