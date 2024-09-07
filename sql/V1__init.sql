CREATE TABLE clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT ,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE hotel (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE chambre (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero INT NOT NULL UNIQUE,
    isDisponible BIT DEFAULT 0
);

CREATE TABLE reservations (
     id INT PRIMARY KEY AUTO_INCREMENT,
     id_client INT,
     id_chambre INT,
     date_debut DATE NOT NULL,
     date_fin DATE NOT NULL,
     FOREIGN KEY (id_client) REFERENCES clients(id_client),
     FOREIGN KEY (id_chambre) REFERENCES chambre(id),
     CHECK (date_debut < date_fin)
);


