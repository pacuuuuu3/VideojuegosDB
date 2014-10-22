/* Clase para probar la clase Consola. */
package videojuegosdb.modelo.test;

import org.junit.Assert;
import org.junit.Test;
import java.sql.*;
import videojuegosdb.modelo.*;
import java.util.*;

/**
 * Clase para probar la clase {@link Consola}
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class TestConsola {

    /**
     * Prueba unitaria para el método getId.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetId() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
	String sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio     INTEGER\n" +
	    ");"; 
	Updater.update(sql);
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into consola (nombre) VALUES (\"prueba" + i + "\");");
	for(int j = 1; j < 5; ++j)
	    Assert.assertTrue(Consola.getId("prueba" + j) == j);
	
    }

    /**
     * Prueba unitaria para el método getConsola.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos. 
     */
    @Test
    public void testGetConsola() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
	String sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio    INTEGER\n" +
	    ");";
	Updater.update(sql);
	Consola c;
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into consola (nombre, anio) VALUES (\"prueba" + i + "\", " + i + ");");
	for(int j = 1; j < 5; ++j){
	    c = Consola.getConsola(j);
	    Assert.assertTrue(c.getNombre().equals("prueba" + j));
	    Assert.assertTrue(c.getAnio() == j);
	}
    }
    
    /**
     * Prueba unitaria para el método getCompania.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetCompania() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
	Updater.update("DROP TABLE publico_consola;");
	Updater.update("DROP TABLE compania;");
	String sql = "CREATE TABLE compania(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio_fundacion     INTEGER\n" +
	    ");";
        Updater.update(sql);
	sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio    INTEGER\n" +
	    ");";
	Updater.update(sql);
	sql = "CREATE TABLE publico_consola(\n" +
	    "id_consola         INTEGER UNIQUE NOT NULL,\n" +
	    "id_compania        INTEGER NOT NULL\n" +
	    ");";
	Updater.update(sql);
	Consola c;
	for(int i = 1; i < 5; ++i){ 
	    Updater.update("INSERT into consola (nombre, anio) VALUES (\"prueba" + i + "\", " + i + ");");
	    Updater.update("INSERT into publico_consola (id_consola, id_compania) VALUES (" + i + ", " + i + ");");
	    Updater.update("INSERT into compania (nombre, anio_fundacion) VALUES (\"prueba" + i + "\", " + i + ");");
	}
	for(int j = 1; j < 5; ++j){
	    c = Consola.getConsola(j);
	    Assert.assertTrue(c.getCompania().equals("prueba" + j));
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
	Updater.update("DROP TABLE publico_consola;");
	Updater.update("DROP TABLE salio_para;");
	String sql = "CREATE TABLE publico_consola(\n" +
	    "id_consola         INTEGER UNIQUE NOT NULL,\n" +
	    "id_compania        INTEGER NOT NULL\n" +
	    ");";
	Updater.update(sql);
	sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio    INTEGER\n" +
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
	Consola c;
	for(int i = 1; i < 5; ++i){ 
	    Updater.update("INSERT into consola (nombre, anio) VALUES (\"prueba" + i + "\", " + i + ");");
	    Updater.update("INSERT into publico_consola (id_consola, id_compania) VALUES (" + i + ", " + i + ");");
	    Updater.update("INSERT into salio_para (id_juego, id_consola) VALUES (\"prueba" + i + "\", " + i + ");");
	}
	for(int j = 1; j < 5; ++j){
	    c = Consola.getConsola(j);
	    c.remove();
	    Assert.assertTrue(Consola.getConsola(j) == null);
	    ResultSet rs = Updater.search("SELECT * FROM publico_consola WHERE id_compania = " + j + ";");
	    Assert.assertFalse(rs.next());
	    rs = Updater.search("SELECT * FROM salio_para WHERE id_consola = " + j + ";");
	    Assert.assertFalse(rs.next());
	}
    }

    /**
     * Prueba unitaria para el método getConsolas.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetConsolas() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
	String sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio     INTEGER\n" +
	    ");";
	Updater.update(sql);
      	ResultSet rs = Updater.search("SELECT * FROM consola;");
	Assert.assertTrue(Consola.getConsolas(rs, "consola").isEmpty());
	for(int i = 1; i < 5; ++i)
	    Updater.update("INSERT into consola (nombre, anio) VALUES (\"prueba" + i + "\", " + i + ");");
	ResultSet r1 = Updater.search("SELECT * FROM consola ORDER BY id ASC;");
	List<Consola> l1 = Consola.getConsolas(r1, "consola");
	int k = 1;
	for(Consola c: l1){
	    Assert.assertTrue(c.getNombre().equals("prueba" + k));
	    Assert.assertTrue(c.getAnio() == k);
	    k++;
	}
    }

    /**
     * Prueba unitaria para el método getAll.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetAll() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE consola;");
		String sql = "CREATE TABLE consola(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio     INTEGER\n" +
	    ");";
	Updater.update(sql);
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into consola (nombre, anio) VALUES (\"prueba" + i + "\", " + i + ");");
	List<String> l = Consola.getAll();
	int j = 1;
	for(String s: l){
	    Assert.assertTrue(s.equals("prueba" + j));
	    j++;
	}
    }
}
