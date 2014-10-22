/* Clase para probar la clase Videojuego. */
package videojuegosdb.modelo.test;

import org.junit.Assert;
import org.junit.Test;
import videojuegosdb.modelo.*;
import java.sql.*;
import java.util.*;

/**
 * Clase para probar la clase {@link Videojuego}
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class TestVideojuego {
    
    /**
     * Prueba unitaria para el método estático getNombre.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetNombre() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE videojuego;");
	String sql = "CREATE TABLE videojuego(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL\n" +
	    ");";
	Updater.update(sql);
	String c;
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into videojuego (nombre) VALUES (\"prueba" + i + "\");");
	for(int j = 1; j < 5; ++j){
	    c = Videojuego.getNombre(j);
	    Assert.assertTrue(c.equals("prueba" + j));
	}	
    }
    
    /**
     * Prueba unitaria para el método getId.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetId() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE videojuego;");
	String sql = "CREATE TABLE videojuego(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL\n" +
	    ");"; 
	Updater.update(sql);
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into videojuego (nombre) VALUES (\"prueba" + i + "\");");
	for(int j = 1; j < 5; ++j)
	    Assert.assertTrue(Videojuego.getId("prueba" + j) == j);
    }
    
    /**
     * Prueba unitaria para el método agregaJuego.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testAgregaJuego() throws SQLException{
	Videojuego v = new Videojuego("hola");
	Updater.update("DROP TABLE videojuego;");
	String sql = "CREATE TABLE videojuego(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL\n" +
	    ");"; 
	Updater.update(sql);
	v.agregaJuego();
	ResultSet elJuego = Updater.search("SELECT * FROM videojuego WHERE nombre like \"hola\";");
	Assert.assertTrue(elJuego.next());
    }
}
