drop database database_Proyect;
CREATE DATABASE IF NOT EXISTS database_Proyect;

use database_Proyect;

CREATE TABLE IF NOT EXISTS Persona(
dni INT(8) NOT NULL PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellido VARCHAR(20) NOT NULL,
direccion VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Empleado(
dni_Empleado INT(8) NOT NULL PRIMARY KEY,
FOREIGN KEY (dni_Empleado) REFERENCES Persona (dni),
fecha_ing INT(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS Cliente(
dni_Cliente INT(8) PRIMARY KEY,
FOREIGN KEY (dni_Cliente) REFERENCES Persona (dni),
nro_Cliente INT(20) NOT NULL,
estadoCivil enum("CASADO","SOLTERO","DIVORCIADO")
);

CREATE TABLE IF NOT EXISTS Gerente(
dni_Gerente INT(8) NOT NULL PRIMARY KEY,
FOREIGN KEY (dni_Gerente) REFERENCES Empleado (dni_Empleado),
comision INT(10)
);

CREATE TABLE IF NOT EXISTS Mucama(
dni_Mucama INT(8) NOT NULL PRIMARY KEY,
FOREIGN KEY (dni_Mucama) REFERENCES Empleado (dni_Empleado)
);

CREATE TABLE IF NOT EXISTS Tipo(
cod_Tipo VARCHAR(10) NOT NULL PRIMARY KEY,
descrip VARCHAR(50) NOT NULL,
costo INT(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Habitacion(
nro_Habitacion INT(10)  NOT NULL PRIMARY KEY,
dni_Muca INT(8) NOT NULL,
co_Tipo VARCHAR(10) NOT NULL,
FOREIGN KEY (dni_Muca) REFERENCES Mucama (dni_Mucama),
FOREIGN KEY (co_Tipo) REFERENCES Tipo (cod_Tipo),
cantCamas INT(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Alquila(
dia VARCHAR(8) NOT NULL PRIMARY KEY,
nro_hab INT(10) NOT NULL,
monto INT(50) NOT NULL,
cantDias INT(50) NOT NULL,
dni_Cte INT(8) NOT NULL,
FOREIGN KEY (dni_Cte) REFERENCES Cliente (dni_Cliente),
FOREIGN KEY (nro_hab) REFERENCES Habitacion (nro_Habitacion)
);
