/* Controlador para la ventana principal. */
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
 * Controlador para la ventana principal.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class MainController implements Initializable {

    @FXML
    private TableView<Lanzamiento> tabla_top;
    @FXML
    private TableColumn<Lanzamiento, String> columna_nombre;
    @FXML
    private TableColumn<Lanzamiento, String> columna_consola;
    @FXML
    private TableColumn<Lanzamiento, String> columna_compania;
    @FXML
    private TableColumn<Lanzamiento, String> columna_calificacion;

    /**
     * Inicializa la ventana principal (descripciones de los parámetros copiadas
     * directamente de la documentación de la interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columna_consola.setCellValueFactory(new PropertyValueFactory<>("consola"));
        columna_compania.setCellValueFactory(new PropertyValueFactory<>("compania"));
        columna_calificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
        tabla_top.getItems().addAll(Lanzamiento.getLanzamientos(getTop20()));
    }
    
    /* Regresa un ResultSet con los 20 (o menos) mejores videojuegos para ponerlos en la 
    tabla principal. */
    private ResultSet getTop20(){
        ResultSet regreso = Updater.search("SELECT * FROM salio_para ORDER BY "
                + "CALIFICACION DESC LIMIT 20;");
        return regreso;
    }
}
