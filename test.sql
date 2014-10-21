CREATE TABLE videojuego(
id INTEGER PRIMARY KEY AUTOINCREMENT,
nombre             TEXT UNIQUE NOT NULL
);

CREATE TABLE consola(
id INTEGER PRIMARY KEY AUTOINCREMENT,
nombre             TEXT UNIQUE NOT NULL,
anio               INTEGER 
);

CREATE TABLE compania(
id INTEGER PRIMARY KEY AUTOINCREMENT,
nombre             TEXT UNIQUE NOT NULL,
anio_fundacion     INTEGER 
);

CREATE TABLE salio_para(
rowid INTEGER PRIMARY KEY AUTOINCREMENT,  
id_juego           INTEGER NOT NULL,
id_consola         INTEGER NOT NULL,
id_compania        INTEGER,
id_desarrollador   INTEGER,
anio               INTEGER,
clasificacion      TEXT,
calificacion       INTEGER
);

CREATE TABLE publico_consola(
id_consola         INTEGER UNIQUE NOT NULL,
id_compania        INTEGER NOT NULL
);

.save test.db
