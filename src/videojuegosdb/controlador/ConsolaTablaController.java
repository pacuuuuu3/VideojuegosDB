/* Controlador para la tabla de consolas. */
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
import videojuegosdb.modelo.Consola;
import videojuegosdb.modelo.Updater;

/**
 * Controlador para la tabla de Consolas.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class ConsolaTablaController implements Initializable {

    @FXML
    private TableView<Consola> tabla;
    @FXML
    private TableColumn<Consola, String> columna_nombre;
    @FXML
    private TableColumn<Consola, String> columna_anio;

    private static String consolas = null;
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
        deployConsolaOnDoubleClick(columna_nombre);
        ResultSet busqueda = Updater.search(consolas);
        columna_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columna_anio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        tabla.getItems().addAll(Consola.getConsolas(busqueda, table));
    }

    /**
     * Establece las consolas a mostrar.
     *
     * @param c - Una consulta de SQLite con los resultados a mostrar.
     */
    public static void setConsolas(String c) {
        consolas = c;
    }

    /**
     * Regresa las consolas a mostrar.
     *
     * @return Una consulta de SQLite con los resultados a mostrar.
     */
    public static String getConsolas() {
        return consolas;
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
     * Cambia la ventana de la interfaz a la de un lanzamiento al hacer doble
     * click en la columna dada.
     *
     * @param nombre - El nombre de la columna a la cual ponerle la propiedad.
     */
    public static void deployConsolaOnDoubleClick(TableColumn<Consola, String> nombre) {
        nombre.setCellFactory(new Callback<TableColumn<Consola, String>, TableCell<Consola, String>>() {
            @Override
            public TableCell<Consola, String> call(TableColumn<Consola, String> col) {
                final TableCell<Consola, String> cell = new TableCell<Consola, String>() {
                    @Override
                    public void updateItem(String consola, boolean empty
                    ) {
                        super.updateItem(consola, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(consola);
                        }
                    }
                };
                cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() > 1) {
                            try {
                                Consola l = (Consola) cell.getTableRow().getItem();
                                if (l == null) {
                                    return;
                                }
                                VideojuegosDBMain.getInstance().gotoConsola(l);
                            } catch (IOException e) {
                                System.err.println("Error en el método "
                                        + "ConsolaTablaController.deployLanzamientoOnDoubleClick(): "
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
