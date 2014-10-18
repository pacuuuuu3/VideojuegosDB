CREATE TABLE videojuego(
id INTEGER PRIMARY KEY AUTOINCREMENT,
nombre             TEXT UNIQUE NOT NULL
);

CREATE TABLE consola(
id INTEGER PRIMARY KEY AUTOINCREMENT,
nombre             TEXT UNIQUE NOT NULL,
anio               INTEGER NOT NULL
);

CREATE TABLE compania(
id INTEGER PRIMARY KEY AUTOINCREMENT,
nombre             TEXT UNIQUE NOT NULL,
anio_fundacion     INTEGER 
);

CREATE TABLE salio_para(
id_juego           INTEGER NOT NULL,
id_consola         INTEGER NOT NULL,
id_compania        INTEGER,
id_desarrollador   INTEGER,
anio               INTEGER NOT NULL,
clasificacion      TEXT NOT NULL,
calificacion       INTEGER
);

CREATE TABLE publico_consola(
id_consola         INTEGER NOT NULL,
id_compania        INTEGER NOT NULL
);

INSERT INTO videojuego (nombre) VALUES
('Destiny'),
('Pokemon X'),
('The Evil Within'),
('Super Smash Bros. for Nintendo 3DS'),
('Grand Theft Auto V'),
('The Elder Scrolls V: Skyrim'),
('League of Legends'),
('Borderlands: The Pre-Sequel');

INSERT INTO consola (nombre, anio) VALUES
('PlayStation 4', 2013),
('Xbox 360', 2005),
('PlayStation 3', 2006),
('Xbox One', 2013),
('Nintendo 3DS', 2011),
('PC', 1982),
('Macintosh', 1984),
('Linux', 1991);

INSERT INTO compania (nombre, anio_fundacion) VALUES
('Activision', 1979),
('Bungie', 1991),


INSERT INTO compania(nombre) VALUES
('Wrightchoice Software');


.save videojuegos.db

