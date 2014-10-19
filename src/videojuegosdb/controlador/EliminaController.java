/* Controlador para la ventana de eliminar. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de eliminar.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class EliminaController implements Initializable {

    @FXML
    private Label seguro;

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
        seguro.setText("¿Seguro que deasea eliminar " + nombre + "?");
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
    private void remove() throws SQLException {
        switch (clase) {
            case "Lanzamiento":
                LanzamientoController.getLanzamiento().remove();
                break;
            /*case "Compania":
                CompaniaController.getCompania().remove();
                break;
            case "Consola":
                ConsolaController.getConsola().remove();*/
        }
    }

    /**
     * Manejador para el botón Sí.
     * 
     * @throws java.io.IOException Si no se encuentra exito.fxml.
     * @throws java.sql.SQLException Si falla la conexión a la base de datos.
     */
    @FXML
    protected void handleBotonSi() throws IOException, SQLException {
            remove();
            VideojuegosDBMain.getInstance().gotoMain();
            Stage actual = (Stage) seguro.getScene().getWindow();
            Parent root = FXMLLoader.load(EliminaController.class.getResource("/videojuegosdb/vista/exito.fxml"));
            actual.setScene(new Scene(root, 500, 230));
            actual.setTitle("Éxito");
            actual.show();
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
