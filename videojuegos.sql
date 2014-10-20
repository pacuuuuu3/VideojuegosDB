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

INSERT INTO videojuego (nombre) VALUES
('Destiny'),
('Pokemon X'),
('The Evil Within'),
('Super Smash Bros. for Nintendo 3DS'),
('Grand Theft Auto V'),
('The Elder Scrolls V: Skyrim'),
('League of Legends'),
('Borderlands: The Pre-Sequel'),
('Brave Frontier');

INSERT INTO consola (nombre, anio) VALUES
('PlayStation 4', 2013),
('Xbox 360', 2005),
('PlayStation 3', 2006),
('Xbox One', 2013),
('Nintendo 3DS', 2011),
('PC', 1982),
('Macintosh', 1984),
('Linux', 1991),
('iOS', 2001),
('Android', 2008);

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
('Apple', 1976),
('gumi Inc.', 2001),
('HTC', 1997);

INSERT INTO salio_para (id_juego, id_consola, id_compania, id_desarrollador, anio, clasificacion, calificacion) VALUES
(1, 1, 1, 2, 2014, 'T', 76),  
(1, 4, 1, 2, 2014, 'T', 77),  
(2, 5, 3, 4, 2013, 'E', 87),
(3, 1, 5, 6, 2014, 'M', 76),
(3, 4, 5, 6, 2014, 'M', 83),
(3, 6, 5, 6, 2014, 'M', 72),
(4, 5, 3, 7, 2014, 'E10+', 85),
(5, 2, 8, 9, 2013, 'M', 97),
(5, 3, 8, 9, 2013, 'M', 97);


INSERT INTO salio_para (id_juego, id_consola, id_compania, id_desarrollador, anio, clasificacion) VALUES
(1, 2, 1, 2, 2014, 'T'),
(1, 3, 1, 2, 2014, 'T'),
(3, 2, 5, 6, 2014, 'M'), 
(3, 3, 5, 6, 2014, 'M'),
(5, 1, 8, 9, 2014, 'M'),
(5, 4, 8, 9, 2014, 'M'),
(5, 6, 8, 9, 2014, 'M');

INSERT INTO salio_para (id_juego, id_consola, id_compania, id_desarrollador, anio) VALUES
(9, 9, 19, 19, 2013),
(9, 10, 19, 19, 2013);

INSERT INTO publico_consola (id_consola, id_compania) VALUES
(1, 15),
(2, 16),
(3, 15),
(4, 16),
(5, 3),
(6, 17),
(7, 18),
(9, 18), 
(10, 20);

.save videojuegos.db

