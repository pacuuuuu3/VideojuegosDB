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
('Nintendo', 1889),
('Game Freak', 1989),
('Bethesda Softworks', 1986),
('Tango Gameworks', 2010),
('Bandai Namco Games', 2006),
('Rockstar Games', 1998),
('Rockstar North', 2002),
('Bethesda Game Studios', 2001),
('Riot Games', 2006),
('TransGaming Inc.', 2001),
('2K Games', 2005),
('2K Australia', 2010), 
('SCEA', 1993),
('Microsoft Game Studios', 2002),
('Microsoft', 1975),
('Apple', 1976);

INSERT INTO salio_para (id_juego, id_consola, id_compania, id_desarrollador, anio, clasificacion, calificacion) VALUES
(1, 1, 1, 2, 2014, 'T', 76),  
(1, 4, 1, 2, 2014, 'T', 77),  
(2, 5, 3, 4, 2013, 'E', 87),
(3, 1, 5, 6, 2014, 'M', 76),
(3, 4, 5, 6, 2014, 'M', 83),
(3, 6, 5, 6, 2014, 'M', 72);

INSERT INTO salio_para (id_juego, id_consola, id_compania, id_desarrollador, anio, clasificacion) VALUES
(1, 2, 1, 2, 2014, 'T'),
(1, 3, 1, 2, 2014, 'T'),
(3, 2, 5, 6, 2014, 'M'), 
(3, 3, 5, 6, 2014, 'M');

.save videojuegos.db

