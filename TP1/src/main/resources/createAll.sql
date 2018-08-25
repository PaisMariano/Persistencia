DROP SCHEMA IF EXISTS tp1;
CREATE SCHEMA tp1;

USE tp1;

CREATE TABLE especie (
  id int NOT NULL UNIQUE,
  nombre VARCHAR(255) NOT NULL UNIQUE,
  altura int NOT NULL,
  peso int NOT NULL,
  vida int NOT NULL,
  cantidad int NOT NULL,
  tipoBicho VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)

)

ENGINE = InnoDB;