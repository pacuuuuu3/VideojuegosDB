/* Controlador para la ventana principal. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import videojuegosdb.modelo.Compania;
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
        deployLanzamientoOnDoubleClick(columna_nombre);
        columna_nombre.setCellValueFactory(new PropertyValueFactory<>("videojuego"));
        columna_consola.setCellValueFactory(new PropertyValueFactory<>("consola"));
        columna_compania.setCellValueFactory(new PropertyValueFactory<>("compania"));
        columna_calificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
        tabla_top.getItems().addAll(Lanzamiento.getLanzamientos(getTop20()));
    }

    /**
     * Manejador para el Hyperlink de Juegos.
     */
    @FXML
    protected void handleHyperlinkJuegos() {

    }

    /**
     * Manejador para el Hyperlink de Consolas.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleHyperlinkConsolas() throws IOException {
        ConsolaTablaController.setTable("consola");
        VideojuegosDBMain.getInstance().gotoConsolasTabla("SELECT * FROM "
                + "consola;");
    }

    /**
     * Manejador para el Hyperlink de Compañías.
     */
    @FXML
    protected void handleHyperlinkCompanias() {

    }


    /* Regresa un ResultSet con los 20 (o menos) mejores videojuegos para ponerlos en la 
     tabla principal. */
    private ResultSet getTop20() {
        ResultSet regreso = Updater.search("SELECT * FROM salio_para ORDER BY "
                + "CALIFICACION DESC LIMIT 20;");
        return regreso;
    }

    /**
     * Cambia la ventana de la interfaz a la de un lanzamiento al hacer doble
     * click en la columna dada.
     *
     * @param nombre - El nombre de la columna a la cual ponerle la propiedad.
     */
    public static void deployLanzamientoOnDoubleClick(TableColumn<Lanzamiento, String> nombre) {
        nombre.setCellFactory(new Callback<TableColumn<Lanzamiento, String>, TableCell<Lanzamiento, String>>() {
            @Override
            public TableCell<Lanzamiento, String> call(TableColumn<Lanzamiento, String> col) {
                final TableCell<Lanzamiento, String> cell = new TableCell<Lanzamiento, String>() {
                    @Override
                    public void updateItem(String videojuego, boolean empty
                    ) {
                        super.updateItem(videojuego, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(videojuego);
                        }
                    }
                };
                cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() > 1) {
                            try {
                                Lanzamiento l = (Lanzamiento) cell.getTableRow().getItem();
                                if (l == null) {
                                    return;
                                }
                                VideojuegosDBMain.getInstance().gotoLanzamiento(l);
                            } catch (IOException e) {
                                System.err.println("Error en el método "
                                        + "MainController.deployLanzamientoOnDoubleClick(): "
                                        + e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }
                        }
                    }
                });
                return cell;
            }
        });
    }
}
