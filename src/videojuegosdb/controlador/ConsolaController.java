/* Controlador para interfaz de Consolas. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import videojuegosdb.modelo.Compania;
import videojuegosdb.modelo.Consola;

/**
 * Controlador para la ventana de Consola.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class ConsolaController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Hyperlink compania;
    @FXML
    private Label anio;

    private static Consola actual = null;

    /**
     * Inicializa la ventana de la Consola(descripciones de los parámetros
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
            setCompania();
            nombre.setText(actual.getNombre());
            anio.setText(actual.getAnio().toString());
        } catch (SQLException e) {
            System.err.println("Error en el método ConsolaController.initialize"
                    + "(URL, ResourceBudle): " + e.getClass().getName() + ": "
                    + e.getMessage());
            System.exit(0);
        }
    }

    /* Establece el texto del Hyperlink de la compañía. */
    private void setCompania() throws SQLException {
        String c = actual.getCompania();
        if (c == null) {
            compania.setVisible(false);
            return;
        }
        compania.setVisible(true);
        compania.setText(c);
    }

    /**
     * Regresa la Consola actual.
     *
     * @return La Consola mostrada en la ventana.
     */
    public static Consola getConsola() {
        return actual;
    }

    /**
     * Actualiza el valor de la Consola.
     *
     * @param nueva - La nueva Consola a mostrar.
     */
    public static void setConsola(Consola nueva) {
        actual = nueva;
    }

    /**
     * Manejador para el botón de eliminar.
     */
    @FXML
    protected void handleBotonElimina() {
        VideojuegosDBMain.getInstance().showEliminaScene(actual.getNombre(),
                "Consola");
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
     * Manejador para el botón de juegos.
     *
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleBotonJuegos() throws IOException {
        Integer id = Consola.getId(actual.getNombre());
        String l = "SELECT * FROM salio_para WHERE id_consola"
                + " = " + id + ";";
        VideojuegosDBMain.getInstance().gotoLanzamientoTabla(l);
    }

    /**
     * Manejador para el Hyperlink de la Compania.
     *
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     * @throws java.io.IOException Si ocurre un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleHyperlinkCompania() throws SQLException, IOException {
        Integer idCompania = Compania.getId(actual.getCompania());
        Compania com = Compania.getCompania(idCompania);
        VideojuegosDBMain.getInstance().gotoCompania(com);
    }

}
