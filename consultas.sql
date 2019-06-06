USE database_Proyect;
/*DNI de clientes solteros que alquilaron una habitacion matrimonial*/
SELECT dni_Cliente FROM (Habitacion INNER JOIN (Cliente INNER JOIN Alquila ON (dni_Cliente = dni_Cte) AND estadoCivil = "SOLTERO") ON nro_Habitacion = nro_hab) INNER JOIN Tipo ON cod_Tipo = co_Tipo AND descrip = "Matrimonial";

/*Lista de clientes y la cantidad de dinero que pago cada uno en 2013*/
SELECT dni_Cliente,SUM(monto) AS total_pagado FROM Cliente INNER JOIN Alquila ON (dni_Cliente=dni_Cte) WHERE dia_alq BETWEEN '2013-01-01' AND '2013-12-31' GROUP BY dni_Cliente;

/*Clientes que se hospedaron mas de 1 vez en 2017*/
SELECT dni_Cliente FROM Cliente INNER JOIN Alquila ON (dni_Cliente=dni_Cte) WHERE dia_alq BETWEEN '2017-01-01' AND '2017-12-31' GROUP BY dni_Cliente HAVING COUNT(dni_Cliente)>1;

/*Dni y sueldo  de los gerentes*/
SELECT dni_Gerente,sueldo FROM Gerente INNER JOIN Empleado ON dni_Gerente=dni_Empleado;

/*Los dni de los empleados que fueron clientes alguna vez*/
SELECT dni_Empleado FROM Cliente INNER JOIN Empleado ON dni_Cliente=dni_Empleado;

/*El nombre, apellido y el dni de los empleados que fueron clientes*/
SELECT nombre,apellido,dni_Empleado FROM (Cliente INNER JOIN Empleado ON dni_Cliente=dni_Empleado) INNER JOIN Persona ON dni=dni_Cliente;
