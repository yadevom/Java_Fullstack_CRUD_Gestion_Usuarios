create schema javafullstack default character set utf8 collate utf8_bin;

CREATE TABLE javafullstack.users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  last_name VARCHAR(45) NULL,
  phone VARCHAR(45) NULL,
  email VARCHAR(255) NULL,
  password VARCHAR(45) NULL,
  PRIMARY KEY (id));

insert into javafullstack.users (id, name, last_name, phone, email, password) values
(null, 'Ernesto', 'Perez', '12344545', 'ep@gmail.com', 'ep123'),
(null, 'Silvestre', 'Dangond', '44135545', 'sdp@gmail.com', 'sd123'),
(null, 'Simon', 'Bolivar', '45425545', 'sb@gmail.com', 'sb123'),
(null, 'Teresa', 'De Calcuta', '12000545', 'tcp@gmail.com', 'tc123');

select * from javafullstack.users;

-- Cambiar parametro de clave
ALTER TABLE javafullstack.users 
CHANGE COLUMN password password VARCHAR(255) COLLATE 'utf8mb3_bin' NULL DEFAULT NULL;