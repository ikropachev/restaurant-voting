DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS restaurant;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE restaurant
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_name_idx ON RESTAURANT (name);

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                    NOT NULL,
    email            VARCHAR                    NOT NULL,
    password         VARCHAR                    NOT NULL,
    privileged       BOOLEAN   DEFAULT FALSE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE dish
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR      NOT NULL,
    price           INTEGER      NOT NULL,
    restaurant_id   INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    date_time          TIMESTAMP DEFAULT now(),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

