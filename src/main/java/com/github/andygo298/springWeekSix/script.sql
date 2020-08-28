CREATE TABLE genre
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE author
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    date_of_birth DATE         NOT NULL
);

CREATE TABLE book
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    creation_year DATE         NOT NULL,
    genre_id      BIGINT       NOT NULL,
    author_id     BIGINT       NOT NULL,
    FOREIGN KEY (genre_id) references genre (id),
    FOREIGN KEY (author_id) references author (id)
);

INSERT INTO author(name, date_of_birth)
VALUES ('Andrei', '1991-02-20'),
       ('Ivan', '1980-05-15'),
       ('Petr', '1965-11-04'),
       ('Viktor', '1982-03-08');

INSERT INTO genre(name)
VALUES ('Biograpy'),
       ('Detective fiction'),
       ('Fantasy'),
       ('Technical writing'),
       ('Horror');

INSERT INTO book(name, creation_year, genre_id, author_id)
VALUES ('Agrofurer biography', '2010-08-09', 1, 4),
       ('Learn Java', '2013-04-18', 4, 1),
       ('Learn C++', '2009-06-11', 4, 1),
       ('The simple detective story', '2004-10-13', 2, 3),
       ('Rosemary’s Baby', '2011-07-21', 5, 2),
       ('Agrofurer-2 biography', '2020-08-09', 1, 4),
       ('Lord of the Flies', '1998-06-02', 5, 2),
       ('Learn Spring', '2015-02-26', 4, 1),
       ('Hypothermia', '2007-06-20', 5, 2),
       ('The Book of Dust', '2008-11-26', 3, 3),
       ('The Frozen Dead', '2012-10-06', 2, 2),
       ('Rivers of London series', '2011-04-27', 3, 3);