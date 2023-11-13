create database AuditoriaInterna;
use AuditoriaInterna;
drop database AuditoriaInterna;


UPDATE  auditoriainterna.users set role='USER' where id=2;
UPDATE  auditoriainterna.users set role='ADMIN' where id=1;
select *from  auditoriainterna.users ;

CREATE TABLE Empleado (
    dni INT PRIMARY KEY,
    nombre_empleado VARCHAR(50),
    apellido_empleado VARCHAR(50),
    fecha_nac DATE,
    area ENUM('Marketing', 'RRHH', 'Ventas', 'TI'),
    cargo VARCHAR(50)
);

INSERT INTO Empleado (dni, nombre_empleado, apellido_empleado, fecha_nac, area,cargo) VALUES
(87654298, 'Fernando', 'Castillo', '1998-05-15', 'TI','Analista de Datos'),
(76598765, 'Nathanael', 'León', '2005-03-23', 'TI', 'Especialista en Desarrollo Web'),
(45328897, 'Sebastian', 'Sanca', '2003-07-11', 'Ventas','Ejecutivo de Ventas');

update empleado set cargo='',area='TI' where dni=76598765;
Select*from Empleado;

CREATE TABLE Sala (
    codigo_sala INT NOT NULL AUTO_INCREMENT, 
    nombre_sala VARCHAR(50) NOT NULL, 
    capacidad INT NOT NULL,
    PRIMARY KEY (codigo_sala)
);

INSERT INTO Sala ( nombre_sala, capacidad) 
VALUES
('Sala A', 50),
('Sala B', 60),
('Sala C', 70);

select*from Sala;


CREATE TABLE Auditoria (
    codigo_auditoria INT NOT NULL AUTO_INCREMENT,
    nombre_auditoria VARCHAR(50),
    codigo_sala INT NOT NULL,
    fecha DATE,
    observaciones TEXT,
    PRIMARY KEY (codigo_auditoria),
	FOREIGN KEY (codigo_sala) REFERENCES Sala(codigo_sala)
);

INSERT INTO Auditoria (codigo_auditoria,codigo_sala, nombre_auditoria, fecha, observaciones) VALUES
(1001,1, 'Auditoria de control administrativo', '2023-10-20', 'Buen manejo de inventario, personal capacitado, implementación de nuevas tecnologías.'),
(1002,2,'Auditoria sobre manejo de la calidad', '2023-08-18', 'Baja eficiencia en el control de calidad, falta de seguimiento de los clientes, problemas de gestión de inventario.'),
(1003,3, 'Auditoria de la gestión de mantenimiento', '2023-09-16', 'Excelente desempeño en las ventas, implementación exitosa de nuevas estrategias de marketing, enfoque sólido en la satisfacción del cliente.');

SELECT*FROM Auditoria;

CREATE TABLE Entrevista (
    cod_entrevista INT NOT NULL AUTO_INCREMENT , 
    codigo_auditoria INT NOT NULL, 
    observaciones TEXT,
    fecha DATE,
    estado ENUM('Pendiente', 'Aprobado', 'Rechazado') DEFAULT 'Pendiente',
    dni INT NOT NULL,
    pregunta1 TEXT,
    pregunta2 TEXT,
    pregunta3 TEXT,
    PRIMARY KEY (cod_entrevista),
    FOREIGN KEY (codigo_auditoria) REFERENCES Auditoria(codigo_auditoria),
    FOREIGN KEY (dni) REFERENCES Empleado(dni)
);
INSERT INTO Entrevista (cod_entrevista,codigo_auditoria, observaciones, fecha, estado, DNI) VALUES
(245001, 1001, 'El empleado demostró un sólido conocimiento técnico pero carece de habilidades de trabajo en equipo.', '2023-10-20', 'Pendiente', 87654298),
(245002, 1001,'El empleado tiene una gran actitud y habilidades comunicativas destacadas.', '2023-10-11', 'Pendiente', 76598765),
(245003, 1003,'El empleado tiene una amplia red de contactos y experiencia relevante en el campo de ventas.', '2023-09-16', 'Pendiente', 45328897);
select*from Entrevista;