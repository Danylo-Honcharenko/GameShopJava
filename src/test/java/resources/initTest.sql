CREATE TABLE Users (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL
);

CREATE TABLE Game (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    rating FLOAT NOT NULL,
    cost INT NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE User_game (
    user_id INT NOT NULL REFERENCES Users (ID) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT NOT NULL REFERENCES Game (ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Accounts (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    amount INT DEFAULT 0,
    type ENUM ('Visa', 'Mastercard') NOT NULL DEFAULT 'Visa',
    user_id INT NOT NULL REFERENCES Users (id)
);

INSERT INTO Game (name, release_date, rating, cost, description)
VALUES ('The Witcher 3: Wild Hunt', '2015-05-19', 9.7, 300, 'Action role-playing game developed and published by CD Projekt.');

INSERT INTO Game (name, release_date, rating, cost, description)
VALUES ('Red Dead Redemption 2', '2018-10-26', 9.8, 500, 'Action-adventure game developed and published by Rockstar Games.');

INSERT INTO Game (name, release_date, rating, cost, description)
VALUES ('Cyberpunk 2077', '2020-12-10', 6.9, 135, 'Action role-playing video game developed and published by CD Projekt.');

INSERT INTO Game (name, release_date, rating, cost, description)
VALUES ('The Legend of Zelda: Breath of the Wild', '2017-03-03', 9.5, 160, 'Action-adventure game developed and published by Nintendo.');

INSERT INTO Game (name, release_date, rating, cost, description)
VALUES ('Fallout 4', '2015-11-10', 8.7, 120, 'Action role-playing game developed by Bethesda Game Studios.');