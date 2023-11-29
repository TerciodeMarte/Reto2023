CREATE database if not exists vapor;

use vapor;

CREATE table if not exists usuario(
idusuario int(10) unsigned auto_increment,
alias varchar(100),
email varchar(100),
pwd varchar(25),
nombre varchar(25),
apellido1 varchar(25),
apellido2 varchar(25),
fechanac date,
imagen varchar(100),
region int(1) unsigned,
rol int(1),
primary key(idusuario)
);

-- DROP TABLE usuario;

create table if not exists juego(
idjuego int(10) unsigned auto_increment,
titulo varchar(50),
descripcion longtext,
imagen varchar(100),
fecha date,
numdescargas int(10),
precio double,
idusuario int(10) unsigned,
primary key(idjuego),
constraint fk_juego_usuario foreign key (idusuario) references usuario(idusuario)
);

-- DROP TABLE juego;

create table if not exists regulacion(
idregulacion int(10)unsigned auto_increment,
idjuego int(10) unsigned,
region int(1) unsigned,
nivel varchar(25),
primary key(idregulacion),
constraint fk_regulacion_juego foreign key (idjuego) references juego(idjuego)
);

-- DROP TABLE regulacion;

CREATE table if not exists biblioteca(
idbiblioteca int(10) unsigned auto_increment,
idusuario int(10) unsigned,
idjuego int(10) unsigned,
fecha date,
primary key(idbiblioteca),
constraint fk_biblioteca_usuario foreign key (idusuario) references usuario(idusuario),
constraint fk_biblioteca_juego foreign key (idjuego) references juego(idjuego)
);

-- DROP TABLE biblioteca;



