/* Clase principal de los controladores de la interfaz gráfica. */
package videojuegosdb.controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de los controladores de la interfaz gráfica.
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class VideojuegosDBMain extends Application {

    private Stage stage;

    private static VideojuegosDBMain instance;

    /**
     * Constructor vacío. Establece la instancia de la clase como el objeto
     * actual.
     */
    public VideojuegosDBMain() {
        instance = this;
    }

    /**
     * Regresa la instancia de la clase.
     *
     * @return La instancia de la clase.
     */
    public static VideojuegosDBMain getInstance() {
        return instance;
    }

    /**
     * Método principal de javafx para correr la aplicación.
     *
     * @param primaryStage - El escenario principal sobre el que se desplegará
     * la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("VideojuegosDB");
        stage = primaryStage;
        gotoMain();
        primaryStage.show();
    }

    /**
     * Método principal de java (se utiliza sólo para ejecutar jars y tener
     * compatibilidad con Swing).
     *
     * @param args - Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Cambia el contenido de la escena por el de la ventana principal.
     */
    public void gotoMain() {
        try {
            replaceSceneContent("/videojuegosdb/vista/main.fxml");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /* Cambia el contenido de la escena por el del fxml dado. */
    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(VideojuegosDBMain.class.getResource(fxml),
                null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 800, 600);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

}
