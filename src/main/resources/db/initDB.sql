DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE restaurants
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON RESTAURANTS (name);

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                    NOT NULL,
    email            VARCHAR                    NOT NULL,
    password         VARCHAR                    NOT NULL,
    privileged       BOOLEAN   DEFAULT FALSE    NOT NULL,
    vote_date_time   TIMESTAMP DEFAULT now()    NOT NULL,
    restaurant_id    INTEGER   DEFAULT 100000   NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE dishes
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR      NOT NULL,
    price           INTEGER      NOT NULL,
    restaurant_id   INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

