/* Controlador para la ventana de un lanzamiento. */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import videojuegosdb.modelo.Compania;
import videojuegosdb.modelo.Consola;
import videojuegosdb.modelo.Lanzamiento;

/**
 * Controlador para la ventana de un lanzamiento.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class LanzamientoController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private ImageView clasificacion;
    @FXML
    private Hyperlink consola;
    @FXML
    private Hyperlink compania;
    @FXML
    private Hyperlink desarrollador;
    @FXML
    private Label anio;
    @FXML
    private Label calificacion;

    private static Lanzamiento actual = null;

    /**
     * Inicializa la ventana del Lanzamiento(descripciones de los parámetros
     * copiadas directamente de la documentación de la interfaz Initializable).
     *
     * @param url - The location used to resolve relative paths for the root
     * object, or null if the location is not known.
     * @param rb - The resources used to localize the root object, or null if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClasificacion();
        setCalificacion();
        nombre.setText(actual.getVideojuego());
        consola.setText(actual.getConsola());
        String comp, dev;
        if (((comp = actual.getCompania()) == null) || comp.isEmpty()) {
            compania.setVisible(false);
        } else {
            compania.setVisible(true);
            compania.setText(comp);
        }
        if (((dev = actual.getCompania()) == null) || dev.isEmpty()) {
            desarrollador.setVisible(false);
        } else {
            desarrollador.setVisible(true);
            desarrollador.setText(dev);
        }
        Integer y = actual.getAnio();
        if (y != 0) {
            anio.setText(y.toString());
        }else
            anio.setVisible(false);
    }

    /* Elige la imagen para la clasificación del juego. */
    private void setClasificacion() {
        String rating = actual.getClasificacion();
        if (rating == null) {
            clasificacion.setVisible(false);
            return;
        }
        clasificacion.setVisible(true);
        switch (rating) {
            case "E":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/everyone.png")));
                break;
            case "E10+":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/everyone10.png")));
                break;
            case "T":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/teen.png")));
                break;
            case "M":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/mature.png")));
                break;
            case "AO":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/adults_only.png")));
                break;
            case "EC":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/early_childhood.png")));
                break;
            case "RP":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/rating_pending.png")));
                break;
            case "K-A":
                clasificacion.setImage(new Image(LanzamientoController.class.getResourceAsStream("clasificaciones/kids_to_adults.png")));
                break;
            default:
                clasificacion.setVisible(false);
                break;
        }
    }

    /* Le pone texto y color a la etiqueta de calificación. */
    private void setCalificacion() {
        Integer c = actual.getCalificacion();
        if (c == null) {
            calificacion.setVisible(false);
        } else {
            calificacion.setVisible(true);
            calificacion.setText(c.toString());
            if (c <= 49) {
                calificacion.setTextFill(Color.web("#FC2609"));
            } else if (c <= 74) {
                calificacion.setTextFill(Color.web("#FEE506"));
            } else {
                calificacion.setTextFill(Color.web("#3CFE06"));
            }
        }
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
     * Manejador para el botón de eliminar.
     */
    @FXML
    protected void handleBotonElimina() {
        VideojuegosDBMain.getInstance().showEliminaScene(actual.getVideojuego(),
                "Lanzamiento");
    }

    /**
     * Manejador para el Hyperlink de Consola.
     *
     * @throws java.io.IOException Si hay un error al intentar cambiar la
     * escena.
     */
    @FXML
    protected void handleHyperlinkConsola() throws IOException {
        Integer idConsola = Consola.getId(actual.getConsola());
        Consola con = Consola.getConsola(idConsola);
        VideojuegosDBMain.getInstance().gotoConsola(con);
    }

    /**
     * Manejador para el Hyperlink de Compañía.
     *
     * @throws java.io.IOException Si ocurre un error al cambiar la escena.
     */
    @FXML
    protected void handleHyperlinkCompania() throws IOException {
        Integer idCompania = Compania.getId(actual.getCompania());
        Compania com = Compania.getCompania(idCompania);
        VideojuegosDBMain.getInstance().gotoCompania(com);
    }

    /**
     * Manejador para el Hyperlink de Desarrollador.
     *
     * @throws java.io.IOException Si ocurre un error al cambiar de escena.
     */
    @FXML
    protected void handleHyperlinkDesarrollador() throws IOException {
        Integer id = Compania.getId(actual.getDesarrollador());
        Compania dev = Compania.getCompania(id);
        VideojuegosDBMain.getInstance().gotoCompania(dev);
    }

    /**
     * Actualiza el valor del Lanzamiento de la ventana.
     *
     * @param nuevo - El nuevo valor para el Lanzamiento de la ventana.
     */
    public static void setLanzamiento(Lanzamiento nuevo) {
        actual = nuevo;
    }

    /**
     * Regresa el Lanzamiento actual.
     *
     * @return El lanzamiento actual.
     */
    public static Lanzamiento getLanzamiento() {
        return actual;
    }

}
