/* Controlador para la tabla de compañías. */
package videojuegosdb.controlador;

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
import videojuegosdb.modelo.Updater;

/**
 * Controlador para la tabla de Companias.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class CompaniaTablaController implements Initializable {

    @FXML
    private TableView<Compania> tabla;
    @FXML
    private TableColumn<Compania, String> columna_nombre;
    @FXML
    private TableColumn<Compania, String> columna_anio;

    private static String companias = null;
    private static String table = null;

    /**
     * Inicializa la tabla de Consolas con las consolas a mostrar(descripciones
     * de los parámetros copiadas directamente de la documentación de la
     * interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deployCompaniaOnDoubleClick(columna_nombre);
        ResultSet busqueda = Updater.search(companias);
        columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columna_anio.setCellValueFactory(new PropertyValueFactory<>("anioFundacion"));
        tabla.getItems().addAll(Compania.getCompanias(busqueda, table));
    }

    /**
     * Establece las compañías a mostrar.
     *
     * @param c - Una consulta de SQLite con los resultados a mostrar.
     */
    public static void setCompanias(String c) {
        companias = c;
    }

    /**
     * Regresa las compañías a mostrar.
     *
     * @return Una consulta de SQLite con los resultados a mostrar.
     */
    public static String getCompanias() {
        return companias;
    }

    /**
     * Actualiza el valor de la tabla de la que sacaremos las consolas.
     *
     * @param t - El nuevo valor de la tabla.
     */
    public static void setTable(String t) {
        table = t;
    }

    /**
     * Regresa el valor de la tabla de la que sacaremos las consolas.
     *
     * @return El nombre de la tabla de la que sacaremos las consolas.
     */
    public static String getTable() {
        return table;
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
     * Cambia la ventana de la interfaz a la de una compañía al hacer doble
     * click en la columna dada.
     *
     * @param nombre - El nombre de la columna a la cual ponerle la propiedad.
     */
    public static void deployCompaniaOnDoubleClick(TableColumn<Compania, String> nombre) {
        nombre.setCellFactory(new Callback<TableColumn<Compania, String>, TableCell<Compania, String>>() {
            @Override
            public TableCell<Compania, String> call(TableColumn<Compania, String> col) {
                final TableCell<Compania, String> cell = new TableCell<Compania, String>() {
                    @Override
                    public void updateItem(String compania, boolean empty
                    ) {
                        super.updateItem(compania, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(compania);
                        }
                    }
                };
                cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() > 1) {
                            try {
                                Compania l = (Compania) cell.getTableRow().getItem();
                                if (l == null) {
                                    return;
                                }
                                VideojuegosDBMain.getInstance().gotoCompania(l);
                            } catch (Exception e) {
                                System.err.println("Error en el método "
                                        + "CompaniaTablaController.deployCompaniaOnDoubleClick(): "
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
