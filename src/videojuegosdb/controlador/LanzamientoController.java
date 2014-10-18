/* Controlador para la ventana de un lanzamiento. */
package videojuegosdb.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import videojuegosdb.modelo.Lanzamiento;

/**
 * Controlador para la ventana de un lanzamiento.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class LanzamientoController implements Initializable {

    @FXML
    private static Label nombre;
    @FXML
    private static ImageView clasificacion;
    @FXML
    private static Hyperlink consola;
    @FXML
    private static Hyperlink compania;
    @FXML
    private static Hyperlink desarrollador;
    @FXML
    private static Label anio;
    @FXML
    private static Label calificacion;

    private static Lanzamiento actual = null;

    /**
     * Inicializa la ventana del lanzamiento(descripciones de los parámetros
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
        if(((comp = actual.getCompania()) == null) || comp.isEmpty())
            compania.setVisible(false);
        else
            compania.setText(comp);
        if(((dev = actual.getCompania()) == null) || dev.isEmpty())
            desarrollador.setVisible(false);
        else
            desarrollador.setText(dev);
        anio.setText(actual.getAnio().toString());
    }

    /* Elige la imagen para la clasificación del juego. */
    private void setClasificacion() {
        switch (actual.getClasificacion()) {
            case "E":
                clasificacion.setImage(new Image("/clasificaciones/everyone.png"));
                break;
            case "E10+":
                clasificacion.setImage(new Image("/clasificaciones/everyone10.png"));
                break;
            case "T":
                clasificacion.setImage(new Image("/clasificaciones/teen.png"));
                break;
            case "M":
                clasificacion.setImage(new Image("/clasificaciones/mature.png"));
                break;
            case "AO":
                clasificacion.setImage(new Image("/clasificaciones/adults_only.png"));
                break;
            case "EC":
                clasificacion.setImage(new Image("/clasificaciones/early_childhood.png"));
                break;
            case "RP":
                clasificacion.setImage(new Image("/clasificaciones/rating_pending.png"));
                break;
            case "K-A":
                clasificacion.setImage(new Image("/clasificaciones/kids_to_adults.png"));
                break;
        }
    }

    /* Le pone texto y color a la etiqueta de calificación. */
    private void setCalificacion() {
        Integer c = actual.getCalificacion();
        if (c == null) {
            calificacion.setVisible(false);
            return;
        }
        calificacion.setText(c.toString());
        if (c <= 49) {
            calificacion.setTextFill(Color.web("#FC2609"));
        } else if (c <= 74) {
            calificacion.setTextFill(Color.web("#FEE506"));
        } else {
            calificacion.setTextFill(Color.web("#3CFE06"));
        }
    }

    /**
     * Actualiza el valor del Lanzamiento de la ventana.
     *
     * @param nuevo - El nuevo valor para el Lanzamiento de la ventana.
     */
    public static void setLanzamiento(Lanzamiento nuevo) {
        actual = nuevo;
    }

}
