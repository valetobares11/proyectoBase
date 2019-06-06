drop database database_Proyect;
CREATE DATABASE IF NOT EXISTS database_Proyect;

USE database_Proyect;

CREATE TABLE IF NOT EXISTS Persona(
dni INT(10) NOT NULL PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellido VARCHAR(20) NOT NULL,
direccion VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Empleado(
dni_Empleado INT(8) NOT NULL PRIMARY KEY,
FOREIGN KEY (dni_Empleado) REFERENCES Persona (dni),
fecha_ing DATE NOT NULL,
sueldo REAL NOT NULL

);

CREATE TABLE IF NOT EXISTS Cliente(
dni_Cliente INT(10) PRIMARY KEY,
FOREIGN KEY (dni_Cliente) REFERENCES Persona (dni),
nro_Cliente INT(20) NOT NULL ,
estadoCivil enum("CASADO","SOLTERO","DIVORCIADO")
);

CREATE TABLE IF NOT EXISTS Gerente(
dni_Gerente INT(10) NOT NULL PRIMARY KEY,
FOREIGN KEY (dni_Gerente) REFERENCES Empleado (dni_Empleado) ON DELETE CASCADE,
comision INT(10)
);

CREATE TABLE IF NOT EXISTS Mucama(
dni_Mucama INT(10) NOT NULL PRIMARY KEY,
FOREIGN KEY (dni_Mucama) REFERENCES Empleado (dni_Empleado) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Tipo(
cod_Tipo INT(10) PRIMARY KEY AUTO_INCREMENT,
descrip VARCHAR(50) NOT NULL,
costo REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS InfoAuditoria(
  id_cambio INTEGER PRIMARY KEY AUTO_INCREMENT,
  cod_Tipo INT(10) NOT NULL,
  precio_anterior REAL NOT NULL,
  precio_nuevo REAL NOT NULL,
  usuario VARCHAR(50) NOT NULL,
  fecha DATE NOT NULL,
  FOREIGN KEY (cod_Tipo) REFERENCES Tipo (cod_Tipo) ON UPDATE CASCADE
);

delimiter $$
CREATE TRIGGER Generar_Info AFTER UPDATE ON Tipo
  FOR EACH ROW
  BEGIN
    IF OLD.costo <> NEW.costo THEN
      INSERT INTO InfoAuditoria(cod_Tipo,precio_anterior, precio_nuevo,usuario,fecha)
      VALUES(OLD.cod_Tipo,OLD.costo, NEW.costo,CURRENT_USER(),CURDATE());
    END IF;
  END;
  $$
delimiter ;

CREATE TABLE IF NOT EXISTS Habitacion(
nro_Habitacion INT(10)  NOT NULL PRIMARY KEY AUTO_INCREMENT,
dni_Muca INT(10) NOT NULL,
co_Tipo INT(10) NOT NULL ,
FOREIGN KEY (dni_Muca) REFERENCES Mucama (dni_Mucama),
FOREIGN KEY (co_Tipo) REFERENCES Tipo (cod_Tipo) ON UPDATE CASCADE,
cantCamas INT(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Fecha(
  dia DATE PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Alquila(
dia_alq DATE NOT NULL,
nro_hab INT(10) NOT NULL,
monto REAL NOT NULL,
cantDias INT(50) NOT NULL,
dni_Cte INT(10) NOT NULL,
FOREIGN KEY (dia_alq) REFERENCES Fecha (dia),
FOREIGN KEY (dni_Cte) REFERENCES Cliente (dni_Cliente),
FOREIGN KEY (nro_hab) REFERENCES Habitacion (nro_Habitacion),
PRIMARY KEY(dia_alq,nro_hab),
UNIQUE(dia_alq,dni_Cte)
);

delimiter $$
CREATE TRIGGER Fecha_Valida BEFORE INSERT ON Alquila
  FOR EACH ROW
  BEGIN
    IF NEW.dia_alq <'2010-01-01' THEN
      SIGNAL SQLSTATE '45000';
    END IF;
  END;
  $$
delimiter ;
