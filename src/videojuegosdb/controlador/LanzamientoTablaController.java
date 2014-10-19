/* Controlador para la ventana con resultados de búsqueda de Lanzamientos. */
package videojuegosdb.controlador;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import videojuegosdb.modelo.Lanzamiento;
import videojuegosdb.modelo.Updater;

/**
 * Controlador para la ventana con resultados de búsqueda de Lanzamientos.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class LanzamientoTablaController implements Initializable {

    @FXML
    private TableView<Lanzamiento> tabla;
    @FXML
    private TableColumn<Lanzamiento, String> columna_nombre;
    @FXML
    private TableColumn<Lanzamiento, String> columna_consola;
    @FXML
    private TableColumn<Lanzamiento, String> columna_compania;
    @FXML
    private TableColumn<Lanzamiento, String> columna_calificacion;

    private static String lanzamientos = null;

    /**
     * Inicializa la tabla de resultados con la información necesaria
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
        MainController.deployLanzamientoOnDoubleClick(columna_nombre);
        ResultSet busqueda = Updater.search(lanzamientos);
        columna_nombre.setCellValueFactory(new PropertyValueFactory<>("videojuego"));
        columna_consola.setCellValueFactory(new PropertyValueFactory<>("consola"));
        columna_compania.setCellValueFactory(new PropertyValueFactory<>("compania"));
        columna_calificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
        tabla.getItems().addAll(Lanzamiento.getLanzamientos(busqueda));
    }
    
    /**
     * Manejador para el botón de regresar.
     */
    @FXML
    protected void handleBotonRegresa() {
        VideojuegosDBMain.getInstance().gotoPreviousScene();
    }

    /**
     * Manejador del botón home.
     */
    @FXML
    protected void handleBotonHome() {
        VideojuegosDBMain.getInstance().gotoMain();
    }
    

    /**
     * Establece los lanzamientos a mostrar.
     *
     * @param l - Una consulta de SQLite con los resultados a mostrar.
     */
    public static void setLanzamientos(String l) {
        lanzamientos = l;
    }

    /**
     * Regresa los lanzamientos a mostrar.
     *
     * @return Una consulta de SQLite con los resultados a mostrar.
     */
    public static String getLanzamientos() {
        return lanzamientos;
    }

}
