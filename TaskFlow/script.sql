/* SCRIPT DE CREACION DE LA BASE DE DATOS*/
CREATE DATABASE gestion_tareas;

USE gestion_tareas;

CREATE TABLE usuarios (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE categorias (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE tareas (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(100) NOT NULL,
  descripcion TEXT NOT NULL,
  estado VARCHAR(20) NOT NULL,
  usuario_id INT NOT NULL,
  categoria_id INT NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  FOREIGN KEY (categoria_id) REFERENCES categorias(id)
) ENGINE=InnoDB;

/*SCRIPT DE LOS INSERTS*/
INSERT INTO usuarios (nombre, email, password) VALUES
('Juan Pérez', 'juan@example.com', 'password1'),
('María López', 'maria@example.com', 'password2'),
('Pedro Ramírez', 'pedro@example.com', 'password3'),
('Laura Martínez', 'laura@example.com', 'password4'),
('Ana Sánchez', 'ana@example.com', 'password5');

INSERT INTO categorias (nombre) VALUES
('Trabajo'),
('Estudios'),
('Hogar'),
('Salud'),
('Entretenimiento');

INSERT INTO tareas (titulo, descripcion,estado, usuario_id, categoria_id) VALUES
('Reunión con el cliente', 'Presentar propuesta de proyecto','pendiente', 1, 1),
('Estudiar para el examen', 'Repasar apuntes y hacer ejercicios', 'pendiente', 2, 2),
('Limpiar la casa', 'Barrer, trapear y ordenar los muebles', 'completada', 3, 3),
('Hacer ejercicio', 'Ir al gimnasio y hacer una rutina de entrenamiento', 'pendiente', 4, 4),
('Ver película', 'Ver la película "El Padrino"', 'completada', 5, 5);
