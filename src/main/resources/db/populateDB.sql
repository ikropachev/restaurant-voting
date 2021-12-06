DELETE FROM user_roles;
DELETE FROM dish;
DELETE FROM restaurant;
DELETE FROM users;
DELETE FROM menu;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANT (NAME) VALUES
    ('BarZero'),                                                       -- 100000
    ('Burgers'),                                                       -- 100001
    ('HappyShaverma'),                                                 -- 100002
    ('Suluguni');                                                      -- 100003

INSERT INTO USERS (NAME, EMAIL, PASSWORD, REGISTERED) VALUES
    ('Admin', 'admin@gmail.com', 'admin_pass', '2021-12-02 18:00'),                  -- 100004
    ('First_user', 'first@gmail.com', 'first_pass', '2021-12-02 18:01'),            -- 100005
    ('Second_user', 'second@gmail.com', 'second_pass', '2021-12-01 18:02');         -- 100006

INSERT INTO menu (restaurant_id, date) VALUES
    (100001, '2021-12-03'),                                                 --100007
    (100002, '2021-12-03'),                                                 --100008
    (100003, '2021-12-03');                                                 --100009

INSERT INTO menu (restaurant_id) VALUES
    (100001),                                                               --100010
    (100002),                                                               --100011
    (100003);                                                               --100012

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID, MENU_ID) VALUES
    ('Hachapuri po imeretinski', 550, 100003, 100009),                         -- 100013
    ('Vegan Burger', 150, 100001, 100007),                                     -- 100014
    ('Chicken Burger', 50, 100001, 100007),                                    -- 100015
    ('Meat Shaverma', 100, 100002, 100008),                                    -- 100016
    ('Vegan Shaverma', 150, 100002, 100008),                                   -- 100017
    ('Chicken Shaverma', 50, 100002, 100011),                                  -- 100018
    ('Hachapuri po adzharski', 350, 100003, 100009),                           -- 100019
    ('Hachapuri po megrelski', 450, 100003, 100012),                           -- 100020
    ('Meat Burger', 100, 100001, 100010);                                       -- 100021

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, DATE_TIME) VALUES
    (100005,100001,'2021-12-02 00:10:05.000000'),                       --100022
    (100005,100002,'2021-12-02 00:11:06.000000'),                       --100023
    (100006,100000,'2021-11-16 20:13:05.000000'),                       --100024
    (100006,100003,'2021-11-17 20:13:05.000000');                       --100025

INSERT INTO VOTE (USER_ID, RESTAURANT_ID) VALUES
    (100005,100001),                                                    --100026
    (100006,100002);                                                    --100027

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100005),
       ('ADMIN', 100004),
       ('USER', 100006);

