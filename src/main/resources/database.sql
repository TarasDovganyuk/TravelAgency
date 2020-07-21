-- Table: users
CREATE TABLE users
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Table: roles
CREATE TABLE roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table for mapping user and role: user_roles
CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
);

-- Create table hotels
CREATE TABLE hotels
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    country VARCHAR (50) NOT NULL
);

-- Create table room type
CREATE TABLE room_type
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    CONSTRAINT room_type_type_uindex
        UNIQUE (type)
);

-- Create table rooms
CREATE TABLE rooms
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    hotel_id  INT NOT NULL,
    room_type INT NULL,
    CONSTRAINT Rooms_hotels_id_fk
        FOREIGN KEY (hotel_id) REFERENCES hotels (id),
    CONSTRAINT room_types_fk
        FOREIGN KEY (room_type) REFERENCES room_type (id)
);

-- Create table orders
CREATE TABLE orders
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    room_id   INT NOT NULL,
    book_date DATE NOT NULL,
    user_id   INT NOT NULL,
    CONSTRAINT orders_rooms_id_fk
        FOREIGN KEY (room_id) REFERENCES rooms (id),
    CONSTRAINT orders_users_id_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Insert data

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_MANAGER');

-- create manager with password 11111111
INSERT INTO users
VALUES (1, 'manager', '$2a$11$T4qPHYW11nxvZ0Itd.1/HOeJnmi/5xstjjHCXO/6ltV2AYXyysTuq');

INSERT INTO user_roles
VALUES (1, 1);

INSERT INTO user_roles
VALUES (1, 2);

INSERT INTO room_type
VALUES (1, 'Economy');

INSERT INTO room_type
VALUES (2, 'Standart');

INSERT INTO room_type
VALUES (3, 'Superior');

INSERT INTO room_type
VALUES (4, 'Luxury');