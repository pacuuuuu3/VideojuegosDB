/* Clase para probar la clase Compañía. */
package videojuegosdb.modelo.test;

import org.junit.Assert;
import org.junit.Test;
import java.sql.*;
import java.util.*;
import videojuegosdb.modelo.*;

/**
 * Clase para probar la clase {@link Compania}
 *
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class TestCompania {

    
    /**
     * Prueba unitaria para el método getId.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetId() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE compania;");
	String sql = "CREATE TABLE compania(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio_fundacion     INTEGER\n" +
	    ");"; 
	Updater.update(sql);
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into compania (nombre) VALUES (\"prueba" + i + "\");");
	for(int j = 1; j < 5; ++j)
	    Assert.assertTrue(Compania.getId("prueba" + j) == j);
	
    }
 
    /**
     * Prueba unitaria para el método getCompania.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetCompania() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE compania;");
	String sql = "CREATE TABLE compania(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio_fundacion     INTEGER\n" +
	    ");";
	Updater.update(sql);
	Compania c;
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into compania (nombre, anio_fundacion) VALUES (\"prueba" + i + "\", " + i + ");");
	for(int j = 1; j < 5; ++j){
	    c = Compania.getCompania(j);
	    Assert.assertTrue(c.getNombre().equals("prueba" + j));
	    Assert.assertTrue(c.getAnioFundacion() == j);
	}
    }

    /**
     * Prueba unitaria para el método remove.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testRemove() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE compania;");
	Updater.update("DROP TABLE publico_consola;");
	String sql = "CREATE TABLE compania(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio_fundacion     INTEGER\n" +
	    ");";
	Updater.update(sql);
	sql = "CREATE TABLE publico_consola(\n" +
	    "id_consola         INTEGER UNIQUE NOT NULL,\n" +
	    "id_compania        INTEGER NOT NULL\n" +
	    ");";
	Updater.update(sql);
	for(int i = 1; i < 5; ++i) {
	    Updater.update("INSERT into compania (nombre, anio_fundacion) VALUES (\"prueba" + i + "\", " + i + ");");
	    Updater.update("INSERT into publico_consola (id_consola, id_compania) VALUES (" + i + ", 1);");
	}
	for(int j = 1; j < 5; ++j){
	    Compania c = Compania.getCompania(j);
	    c.remove();
	    Assert.assertTrue(Compania.getCompania(j) == null);
	    ResultSet rs = Updater.search("SELECT * FROM publico_consola WHERE id_compania = " + j + ";");
	    Assert.assertFalse(rs.next());
	}
    }
    
    /**
     * Prueba unitaria para el método getAll.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetAll() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE compania;");
	String sql = "CREATE TABLE compania(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio_fundacion     INTEGER\n" +
	    ");";
	Updater.update(sql);
	for(int i = 1; i < 5; ++i) 
	    Updater.update("INSERT into compania (nombre, anio_fundacion) VALUES (\"prueba" + i + "\", " + i + ");");
	List<String> l = Compania.getAll();
	int j = 1;
	for(String s: l){
	    Assert.assertTrue(s.equals("prueba" + j));
	    j++;
	}
    }
    
    /**
     * Prueba unitaria para el método getCompanias.
     * @throws java.sql.SQLException Si ocurre un error con la base de datos.
     */
    @Test
    public void testGetCompanias() throws SQLException{
	Conexion.setNombreDB("test.db");
	Updater.update("DROP TABLE compania;");
	String sql = "CREATE TABLE compania(\n" +  
	    "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
	    "nombre             TEXT UNIQUE NOT NULL,\n" +
	    "anio_fundacion     INTEGER\n" +
	    ");";
	Updater.update(sql);
      	ResultSet rs = Updater.search("SELECT * FROM compania;");
	Assert.assertTrue(Compania.getCompanias(rs, "compania").isEmpty());
	for(int i = 1; i < 5; ++i)
	    Updater.update("INSERT into compania (nombre, anio_fundacion) VALUES (\"prueba" + i + "\", " + i + ");");
	ResultSet r1 = Updater.search("SELECT * FROM compania ORDER BY id ASC;");
	List<Compania> l1 = Compania.getCompanias(r1, "compania");
	int k = 1;
	for(Compania c: l1){
	    Assert.assertTrue(c.getNombre().equals("prueba" + k));
	    Assert.assertTrue(c.getAnioFundacion() == k);
	    k++;
	}
    }
    
}
