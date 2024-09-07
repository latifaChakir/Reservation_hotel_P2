CREATE TABLE clients (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         age INT,
                         address VARCHAR(100) NOT NULL,
                         phone VARCHAR(20) NOT NULL
);

CREATE TABLE hotel (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       address VARCHAR(100) NOT NULL,
                       phone VARCHAR(20) NOT NULL
);

CREATE TABLE chambre (
                         id SERIAL PRIMARY KEY,
                         numero INT NOT NULL UNIQUE,
                         isDisponible BOOLEAN DEFAULT FALSE
);

CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              id_client INT,
                              id_chambre INT,
                              date_debut DATE NOT NULL,
                              date_fin DATE NOT NULL,
                              FOREIGN KEY (id_client) REFERENCES clients(id),
                              FOREIGN KEY (id_chambre) REFERENCES chambre(id),
                              CHECK (date_debut < date_fin)
);
