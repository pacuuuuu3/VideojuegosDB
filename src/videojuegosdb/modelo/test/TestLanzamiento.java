/* Clase para probar la clase Lanzamiento. */
package videojuegosdb.modelo.test;

import org.junit.Assert;
import org.junit.Test;
import videojuegosdb.modelo.*;
import java.util.*;
import java.sql.*;

/**
 * Clase para probar la clase {@link Lanzamiento}
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class TestLanzamiento {

    /**
     * Prueba unitaria para el método getLanzamientos.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetLanzamientos() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
	Updater.update("DROP TABLE salio_para;");
	Updater.update("DROP TABLE videojuego;");
	String sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio     INTEGER\n" +
	    ");";
	Updater.update(sql);
	sql = "CREATE TABLE salio_para(\n" +
	    "rowid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "id_juego           INTEGER NOT NULL,\n" +
	    "id_consola         INTEGER NOT NULL,\n" +
	    "id_compania        INTEGER,\n" +
	    "id_desarrollador   INTEGER,\n" +
	    "anio               INTEGER,\n" +
	    "clasificacion      TEXT,\n" +
	    "calificacion       INTEGER\n" +
	    ");\n";
	Updater.update(sql);
	sql = "CREATE TABLE videojuego(\n" +
	    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
	    "nombre             TEXT UNIQUE NOT NULL" +
	    ");";
	Updater.update(sql);
	ResultSet rs = Updater.search("SELECT * FROM salio_para;");
	Assert.assertTrue(Lanzamiento.getLanzamientos(rs).isEmpty());
	for(int i = 1; i < 5; ++i){
	    Updater.update("INSERT into salio_para (id_juego, id_consola) VALUES ( " + i + ", " + i + ");");
	    Updater.update("INSERT into videojuego (nombre) VALUES (\"prueba" + i + "\");");
	    Updater.update("INSERT into consola (nombre) VALUES (\"prueba" + i + "\");");
	}
	ResultSet r1 = Updater.search("SELECT * FROM salio_para ORDER BY rowid ASC;");
	List<Lanzamiento> l1 = Lanzamiento.getLanzamientos(r1);
	int k = 1;
	for(Lanzamiento l: l1){
	    Assert.assertTrue(l.getVideojuego().equals("prueba" + k));
	    Assert.assertTrue(l.getConsola().equals("prueba" + k));  
	    k++;
	}
    }
    
    /**
     * Prueba unitaria para el método remove.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testRemove() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
	Updater.update("DROP TABLE salio_para;");
	Updater.update("DROP TABLE videojuego;");
	String sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio     INTEGER\n" +
	    ");";
	Updater.update(sql);
	sql = "CREATE TABLE salio_para(\n" +
	    "rowid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "id_juego           INTEGER NOT NULL,\n" +
	    "id_consola         INTEGER NOT NULL,\n" +
	    "id_compania        INTEGER,\n" +
	    "id_desarrollador   INTEGER,\n" +
	    "anio               INTEGER,\n" +
	    "clasificacion      TEXT,\n" +
	    "calificacion       INTEGER\n" +
	    ");\n";
	Updater.update(sql);
	sql = "CREATE TABLE videojuego(\n" +
	    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
	    "nombre             TEXT UNIQUE NOT NULL" +
	    ");";
	Updater.update(sql);
	for(int i = 1; i < 5; ++i){
	    Updater.update("INSERT into salio_para (id_juego, id_consola) VALUES ( " + i + ", " + i + ");");
	    Updater.update("INSERT into videojuego (nombre) VALUES (\"prueba" + i + "\");");
	    Updater.update("INSERT into consola (nombre) VALUES (\"prueba" + i + "\");");
	}
	ResultSet rs = Updater.search("SELECT * FROM salio_para");
	List<Lanzamiento> aEliminar = Lanzamiento.getLanzamientos(rs);
	for(Lanzamiento l : aEliminar)
	    l.remove();
	rs = Updater.search("SELECT * FROM salio_para");
	Assert.assertFalse(rs.next());
	rs = Updater.search("SELECT * FROM videojuego");
	Assert.assertFalse(rs.next());	
    }

}
