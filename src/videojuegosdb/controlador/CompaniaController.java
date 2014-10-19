/* Controlador para la ventana de Compania. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import videojuegosdb.modelo.Compania;

/**
 * Controlador para la ventana de Compania.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class CompaniaController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Label anio;

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
        nombre.setText(actual.getNombre());
        anio.setText(actual.getAnioFundacion().toString());
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

    /**
     * Manejador para el botón de eliminar.
     */
    @FXML
    protected void handleBotonElimina() {
        VideojuegosDBMain.getInstance().showEliminaScene(actual.getNombre(),
                "Compania");
    }

    /**
     * Manejador para el botón de regresar.
     */
    @FXML
    protected void handleBotonRegresa() {
        VideojuegosDBMain.getInstance().gotoPreviousScene();
    }

    /**
     * Manejador para el botón home.
     */
    @FXML
    protected void handleBotonHome() {
        VideojuegosDBMain.getInstance().gotoMain();
    }

    /**
     * Manejados para el botón de juegos desarrollados.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleBotonJuegosDesarrollados() throws IOException {
        Integer id = Compania.getId(actual.getNombre());
        VideojuegosDBMain.getInstance().gotoLanzamientoTabla("SELECT * FROM "
                + "salio_para WHERE id_desarrollador = " + id + ";");
    }

    /**
     * Manejador para el botón de juegos publicados.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleBotonJuegosPublicados() throws IOException {
        Integer id = Compania.getId(actual.getNombre());
        VideojuegosDBMain.getInstance().gotoLanzamientoTabla("SELECT * FROM "
                + "salio_para WHERE id_compania = " + id + ";");
    }

    @FXML
    protected void handleBotonConsolas() {

    }

}
