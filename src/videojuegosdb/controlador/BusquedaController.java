/* Controlador para la ventana de búsquedas. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import videojuegosdb.modelo.Compania;
import videojuegosdb.modelo.Consola;
import videojuegosdb.modelo.Lanzamiento;
import videojuegosdb.modelo.Updater;
import videojuegosdb.modelo.Videojuego;

/**
 * Controlador para la ventana de búsquedas.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class BusquedaController implements Initializable {

    @FXML
    private TextField nombre_juego;
    @FXML
    private ComboBox<String> consola_juego;
    @FXML
    private ComboBox<String> compania_juego;
    @FXML
    private ComboBox<String> desarrollador_juego;
    @FXML
    private TextField anio_juego;
    @FXML
    private ComboBox<String> clasificacion_juego;
    @FXML
    private ComboBox<Integer> calificacion_juego;
    @FXML
    private TextField nombre_consola;
    @FXML
    private ComboBox<String> compania_consola;
    @FXML
    private TextField anio_consola;
    @FXML
    private TextField nombre_compania;
    @FXML
    private TextField anio_compania;

    /**
     * Inicializa la ventana de búsquedas (descripciones de los parámetros
     * copiadas directamente de la documentación de la interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<String> companias = Compania.getAll();
            consola_juego.getItems().setAll(Consola.getAll());
            compania_juego.getItems().setAll(companias);
            desarrollador_juego.getItems().setAll(companias);
            clasificacion_juego.getItems().setAll(Lanzamiento.getListaClasificaciones());
            calificacion_juego.getItems().setAll(Lanzamiento.getListaCalificaciones());
            compania_consola.getItems().setAll(companias);
        } catch (SQLException e) {
            System.err.println("Error en el método "
                    + "BusquedaController.initialize(URL, ResourceBundle): "
                    + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Manejador para el botón buscar juego.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleBotonBuscarJuego() throws IOException {
        String sql = "SELECT * FROM salio_para ";
        String nombre = nombre_juego.getText();
        String consola = consola_juego.getValue();
        String compania = compania_juego.getValue();
        String desarrollador = desarrollador_juego.getValue();
        Integer anio;
        try {
            anio = Integer.parseInt(anio_juego.getText());
        } catch (NumberFormatException e) {
            anio = null;
        }
        String clasificacion = clasificacion_juego.getValue();
        Integer calificacion = calificacion_juego.getValue();
        boolean first = true;
        if (!nombre.isEmpty()) {
            if (first) {
                first = false;
                sql += "WHERE ";
            }
            sql += "id_juego = " + Videojuego.getId(nombre) + " ";
        }
        if (!(consola == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "id_consola = " + Consola.getId(consola) + " ";
        }
        if (!(compania == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "id_compania = " + Compania.getId(compania) + " ";
        }
        if (!(desarrollador == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "id_desarrollador = " + Compania.getId(desarrollador) + " ";
        }
        if (!(anio == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "anio = " + anio + " ";
        }
        if (!(clasificacion == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "clasificacion = \"" + clasificacion + "\" ";
        }
        if (!(calificacion == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "calificacion = " + calificacion + " ";
        }
        sql += ";";
        VideojuegosDBMain.getInstance().gotoLanzamientoTabla(sql);
    }

    /**
     * Manejador para el botón de buscar consola.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleBotonBuscarConsola() throws IOException {
        String sql = "SELECT * FROM consola ";
        String nombre = nombre_consola.getText();
        String compania = compania_consola.getValue();
        Integer anio;
        try {
            anio = Integer.parseInt(anio_consola.getText());
        } catch (NumberFormatException e) {
            anio = null;
        }
        boolean first = true;
        if (!nombre.isEmpty()) {
            if (first) {
                first = false;
                sql += "WHERE ";
            }
            sql += "nombre LIKE \"" + nombre + "\" ";
        }
        if (!(compania == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "id IN(SELECT id_consola FROM publico_consola WHERE id_compania"
                    + " = " + Compania.getId(compania) + ") ";
        }
        if (!(anio == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "anio = " + anio + " ";
        }
        sql += ";";
        ConsolaTablaController.setTable("consola");
        VideojuegosDBMain.getInstance().gotoConsolasTabla(sql);
    }

    /**
     * Manejador para el botón de buscar consola.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleBotonBuscarCompania() throws IOException {
        String sql = "SELECT * FROM compania ";
        String nombre = nombre_compania.getText();
        Integer anio;
        try {
            anio = Integer.parseInt(anio_compania.getText());
        } catch (NumberFormatException e) {
            anio = null;
        }
        boolean first = true;
        if (!nombre.isEmpty()) {
            if (first) {
                first = false;
                sql += "WHERE ";
            }
            sql += "nombre LIKE \"" + nombre + "\" ";
        }
        if (!(anio == null)) {
            if (first) {
                first = false;
                sql += "WHERE ";
            } else {
                sql += "AND ";
            }
            sql += "anio_fundacion = " + anio + " ";
        }
        sql += ";";
        CompaniaTablaController.setTable("compania");
        VideojuegosDBMain.getInstance().gotoCompaniasTabla(sql);
    }
}
