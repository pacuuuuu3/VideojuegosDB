/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuegosdb.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import videojuegosdb.modelo.Consola;
import videojuegosdb.modelo.Videojuego;

/**
 * FXML Controller class
 *
 * @author victor
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void handleBotonAgregarJuego() throws IOException {
        error_juego.setVisible(false);
        Integer juegoExistente, consolaExistente, companiaExistente,
                desarrolladorExistente;
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
            error_juego.setText("Por favor escriba el nombre del juego");
            error_juego.setVisible(true);
            return;
        }
        if(consola == null){
            error_juego.setText("Por favor seleccione una consola");
            error_juego.setVisible(true);
            return;
        }
        
        juegoExistente = Videojuego.getId(nombre);
        consolaExistente = Consola.getId(nombre);
        if(juegoExistente != null){
            
        }
    }
}
