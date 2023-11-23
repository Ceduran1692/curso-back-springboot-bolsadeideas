INSERT INTO regiones (nombre) VALUES ('África');
INSERT INTO regiones (nombre) VALUES ('Asia');
INSERT INTO regiones (nombre) VALUES ('Europa');
INSERT INTO regiones (nombre) VALUES ('América del Norte');
INSERT INTO regiones (nombre) VALUES ('América del Sur');
INSERT INTO regiones (nombre) VALUES ('Antártida');
INSERT INTO regiones (nombre) VALUES ('Oceanía');
INSERT INTO regiones (nombre) VALUES ('Asia Central');
INSERT INTO regiones (nombre) VALUES ('América Central');
INSERT INTO regiones (nombre) VALUES ('Caribe');
INSERT INTO regiones (nombre) VALUES ('Oriente Medio');
INSERT INTO regiones (nombre) VALUES ('Sudeste Asiático');
INSERT INTO regiones (nombre) VALUES ('Europa del Este');
INSERT INTO regiones (nombre) VALUES ('Europa del Norte');


INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Lucía', 'Fernández', 'lucia.fernandez@email.com', '2024-04-30', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Alejandro', 'González', 'alejandro.gonzalez@email.com', '2024-05-05', 2);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Elena', 'Díaz', 'elena.diaz@email.com', '2024-06-10', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Martín', 'Torres', 'martin.torres@email.com', '2024-07-15', 4);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Natalia', 'Ruiz', 'natalia.ruiz@email.com', '2024-08-20', 5);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Juan Pablo', 'Sánchez', 'juanpablo.sanchez@email.com', '2024-09-25', 6);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Valentina', 'Hernández', 'valentina.hernandez@email.com', '2024-10-30', 7);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Santiago', 'Martínez', 'santiago.martinez@email.com', '2024-11-05', 8);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Paula', 'Gómez', 'paula.gomez@email.com', '2024-12-10', 9);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Emilio', 'López', 'emilio.lopez@email.com', '2025-01-15', 10);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Luisa', 'Hernández', 'luisa.hernandez@email.com', '2025-02-20', 11);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Simón', 'Martínez', 'simon.martinez@email.com', '2025-03-25', 12);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Camila', 'Gómez', 'camila.gomez@email.com', '2025-04-30', 13);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Matías', 'Ruiz', 'matias.ruiz@email.com', '2025-05-05', 14);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Florencia', 'Sánchez', 'florencia.sanchez@email.com', '2025-06-10', 10);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Renato', 'Hernández', 'renato.hernandez@email.com', '2025-07-15', 1);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Juana', 'Martínez', 'juana.martinez@email.com', '2025-08-20', 2);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Max', 'Gómez', 'max.gomez@email.com', '2025-09-25', 3);
INSERT INTO clientes (nombre, apellido, email, create_at, region_id) VALUES ('Isabella', 'Ruiz', 'isabella.ruiz@email.com', '2025-10-30', 4);

INSERT INTO roles(name) VALUES('ADMINISTRATOR');
INSERT INTO roles(name) VALUES('USER');

INSERT INTO usuarios(apellido, email, nombre, password, username) values ('Admin_nombre', 'Admin_apellido', 'admin@email.com','$2a$10$2Wfttbut6Doz7vsjh6BpvuM5V6yFhUswizRZH0fqkI.cnGwD93uqe', 'admin1');
INSERT INTO usuarios(apellido, email, nombre, password, username) values ('User_nombre', 'User_apellido', 'user@email.com','$2a$10$0oIwnoIm/abTUqqOZSYLjOkPof9Nk3Hd2iSHkDX.V55XJTNZUICea','user1');

INSERT INTO usuarios_roles(usuario_id, role_id) values (1,1);
INSERT INTO usuarios_roles(usuario_id, role_id) values (2,2);