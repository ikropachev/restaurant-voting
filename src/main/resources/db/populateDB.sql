DELETE FROM user_roles;
DELETE FROM dish;
DELETE FROM restaurant;
DELETE FROM users;
DELETE FROM menu;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANT (NAME) VALUES
    ('BarZero'),                                                            --100000
    ('Burgers'),                                                            --100001
    ('HappyShaverma'),                                                      --100002
    ('Suluguni');                                                           --100003

INSERT INTO USERS (NAME, EMAIL, PASSWORD, REGISTERED) VALUES
    ('admin', 'admin@gmail.com', 'admin', '2021-12-02 18:00'),              --100004
    ('user', 'user@gmail.com', 'user', '2021-12-02 18:01'),                 --100005
    ('second_user', 'second@gmail.com', 'second_pass', '2021-12-01 18:02'), --100006
    ('third_user', 'third@gmail.com', 'third_pass', '2021-12-01 18:03');    --100007

INSERT INTO menu (restaurant_id, date) VALUES
    (100001, '2021-12-03'),                                                --100008
    (100002, '2021-12-03'),                                                --100009
    (100003, '2021-12-03'),                                                --100010
    (100001, '2021-12-05');                                                --100011

INSERT INTO menu (restaurant_id) VALUES
    (100002),                                                              --100012
    (100003);                                                              --100013

INSERT INTO DISH (NAME, PRICE, MENU_ID) VALUES
    ('Hachapuri po imeretinski', 550, 100010),                             --100014
    ('Vegan Burger', 150, 100008),                                         --100015
    ('Chicken Burger', 50, 100008),                                        --100016
    ('Meat Shaverma', 100, 100009),                                        --100017
    ('Vegan Shaverma', 150, 100009),                                       --100018
    ('Chicken Shaverma', 50, 100012),                                      --100019
    ('Hachapuri po adzharski', 350, 100010),                               --100020
    ('Hachapuri po megrelski', 450, 100013),                               --100021
    ('Meat Burger', 100, 100011);                                          --100022

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, DATE) VALUES
    (100005,100001,'2021-12-02'),                                         --100023
    (100005,100002,'2021-12-03'),                                         --100024
    (100006,100000,'2021-11-16'),                                         --100025
    (100006,100003,'2021-11-17'),                                         --100026
    (100007,100002,'2021-11-17');                                         --100027

INSERT INTO VOTE (USER_ID, RESTAURANT_ID) VALUES
    (100006,100001),                                                       --100028
    (100007,100002);                                                       --100029

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100005),
       ('ADMIN', 100004),
       ('USER', 100006);