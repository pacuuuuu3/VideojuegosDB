/* Controlador para la ventana de agregar. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import videojuegosdb.modelo.Compania;
import videojuegosdb.modelo.Consola;
import videojuegosdb.modelo.Lanzamiento;
import videojuegosdb.modelo.Updater;
import videojuegosdb.modelo.Videojuego;

/**
 * Controlador para la ventana de agregar.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class AgregarController implements Initializable {

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
    @FXML
    private Label error_juego;
    @FXML
    private Label error_consola;
    @FXML
    private Label error_compania;

    /**
     * Inicializa la ventana para agregar (descripciones de los parámetros
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
     * Manejador para el botón agregar consola.
     *
     * @throws java.io.IOException Si ocurre un error al intentar mostrar la
     * venta de éxito.
     */
    @FXML
    protected void handleBotonAgregarConsola() throws IOException {
        error_consola.setVisible(false);
        String nombre = nombre_consola.getText();
        String compania = compania_consola.getValue();
        Integer anio;
        try {
            anio = Integer.parseInt(anio_consola.getText());
        } catch (NumberFormatException e) {
            anio = null;
        }
        if (nombre.isEmpty()) {
            error_consola.setText("Por favor escriba un nombre");
            error_consola.setVisible(true);
            return;
        }
        boolean companiaNull = (compania == null);
        boolean anioNull = (anio == null);
        String sql = "INSERT INTO consola (nombre";
        if (!anioNull) {
            sql += ", anio";
        }
        sql += ") VALUES (";
        sql += "\"" + nombre + "\"";
        if (!anioNull) {
            sql += ", " + anio;
        }
        sql += ");";
        Updater.update(sql);
        if (!companiaNull) {
            Updater.update("INSERT INTO publico_consola (id_consola,"
                    + " id_compania) VALUES (" + Consola.getId(nombre) + ", "
                    + Compania.getId(compania) + ");");
        }
        VideojuegosDBMain.getInstance().showSuccess();
    }

    /**
     * Manejador para el botón de agregar compañía.
     */
    @FXML
    protected void handleBotonAgregarCompania() {
        error_compania.setVisible(false);
        String nombre = nombre_consola.getText();
        String compania = compania_consola.getValue();
        Integer anio;
        try {
            anio = Integer.parseInt(anio_consola.getText());
        } catch (NumberFormatException e) {
            anio = null;
        }
        if (nombre.isEmpty()) {
            error_consola.setText("Por favor escriba un nombre");
            error_consola.setVisible(true);
            return;
        }
        boolean companiaNull = (compania == null);
        boolean anioNull = (anio == null);
        String sql = "INSERT INTO consola (nombre";
        if (!anioNull) {
            sql += ", anio";
        }
        sql += ") VALUES (";
        sql += "\"" + nombre + "\"";
        if (!anioNull) {
            sql += ", " + anio;
        }
        sql += ");";
        Updater.update(sql);
        if (!companiaNull) {
            Updater.update("INSERT INTO publico_consola (id_consola,"
                    + " id_compania) VALUES (" + Consola.getId(nombre) + ", "
                    + Compania.getId(compania) + ");");
        }
        VideojuegosDBMain.getInstance().showSuccess();
    }
}

/**
 * Manejador para el botón de agregar juego.
 *
 * @throws java.io.IOException Si ocurre un error al llamar a la ventana de
 * éxito.
 */
@FXML
        protected void handleBotonAgregarJuego() throws IOException {
        error_juego.setVisible(false);
        Integer juegoExistente;
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
        if (nombre.isEmpty()) {
            error_juego.setText("Por favor escriba un nombre");
            error_juego.setVisible(true);
            return;
        }
        if (consola == null) {
            error_juego.setText("Por favor seleccione una consola");
            error_juego.setVisible(true);
            return;
        }
        boolean companiaNull = (compania == null);
        boolean desarrolladorNull = (desarrollador == null);
        boolean anioNull = (anio == null);
        boolean clasificacionNull = (clasificacion == null);
        boolean calificacionNull = (calificacion == null);
        String sql = "INSERT INTO salio_para (id_juego, id_consola";
        if (!companiaNull) {
            sql += ", id_compania";
        }
        if (!desarrolladorNull) {
            sql += ", id_desarrollador";
        }
        if (!anioNull) {
            sql += ", anio";
        }
        if (!clasificacionNull) {
            sql += ", clasificacion";
        }
        if (!calificacionNull) {
            sql += ", calificacion";
        }
        sql += ") VALUES (";
        juegoExistente = Videojuego.getId(nombre);
        if (juegoExistente != null) {
            sql += juegoExistente;
        } else {
            Updater.update("INSERT into videojuego (nombre) VALUES (\"" + nombre + "\");");
            sql += Videojuego.getId(nombre);
        }
        sql += ", " + Consola.getId(consola);
        if (!companiaNull) {
            sql += ", " + Compania.getId(compania);
        }
        if (!desarrolladorNull) {
            sql += ", " + Compania.getId(desarrollador);
        }
        if (!anioNull) {
            sql += ", " + anio;
        }
        if (!clasificacionNull) {
            sql += ", \"" + clasificacion + "\"";
        }
        if (!calificacionNull) {
            sql += ", " + calificacion;
        }
        sql += ");";
        Updater.update(sql);
        VideojuegosDBMain.getInstance().showSuccess();
    }

    /**
     * Manejador para el botón home.
     */
    @FXML
        protected void handleBotonHome() {
        VideojuegosDBMain.getInstance().gotoMain();
    }
}
