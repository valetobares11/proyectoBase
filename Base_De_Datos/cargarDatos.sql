INSERT INTO Persona(dni,nombre,apellido,direccion)
VALUES
(1,'Agustin','Borda','aaaa'),
(2,'aaa','aaa','aaa'),
(3,'bbb','bbb','bbb'),
(4,'Valentin','Tobares','cas'),
(5,'ccc','ccc','ccc');

INSERT INTO Empleado(dni_Empleado,fecha_ing,sueldo)
VALUES
(1,'2019-01-01',70000),
(4,'2019-01-02',70000),
(3,'2019-03-01',35000);

INSERT INTO Cliente(dni_Cliente,nro_Cliente,estadoCivil)
VALUES
(2,1,'SOLTERO'),
(5,2,'CASADO');

INSERT INTO Mucama(dni_Mucama)
VALUES
(3),
(2);

INSERT INTO Gerente(dni_Gerente,comision)
VALUES
(1,100);

INSERT INTO Tipo(costo,descrip)
VALUES
(1000,'Griego'),
(1500,'Romano'),
(700,'Matrimonial');

INSERT INTO Habitacion(dni_Muca,co_Tipo,cantCamas)
VALUES
(3,1,5),
(3,2,8);

INSERT INTO Fecha(dia)
VALUES
('2019-05-06'),
('2019-05-05'),
('2009-05-05');

INSERT INTO Alquila(dia_alq,nro_hab,monto,cantDias,dni_Cte)
VALUES
('2019-05-06',1,2000,2,2),
('2019-05-05',2,4500,3,5),
('2009-05-05',2,4500,3,5);

UPDATE Tipo
SET costo = 5000 WHERE true;
