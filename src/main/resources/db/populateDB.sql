DELETE FROM dish;
DELETE FROM restaurant;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANT (NAME) VALUES
    ('BarZero'),                                                       -- 100000
    ('Burgers'),                                                       -- 100001
    ('HappyShaverma'),                                                 -- 100002
    ('Suluguni');                                                      -- 100003

INSERT INTO USERS (NAME, EMAIL, PASSWORD, PRIVILEGED) VALUES
    ('Admin', 'admin@gmail.com', 'admin_pass', TRUE),                  -- 100004
    ('First_user', 'first@gmail.com', 'first_pass', FALSE),            -- 100005
    ('Second_user', 'second@gmail.com', 'second_pass', FALSE);         -- 100006

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID) VALUES
    ('Hachapuri po imeretinski', 550, 100003),                         -- 100007
    ('Vegan Burger', 150, 100001),                                     -- 100008
    ('Chicken Burger', 50, 100001),                                    -- 100009
    ('Meat Shaverma', 100, 100002),                                    -- 100010
    ('Vegan Shaverma', 150, 100002),                                   -- 100011
    ('Chicken Shaverma', 50, 100002),                                  -- 100012
    ('Hachapuri po adzharski', 350, 100003),                           -- 100013
    ('Hachapuri po megrelski', 450, 100003),                           -- 100014
    ('Meat Burger', 100, 100001);                                       -- 100015

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, DATE_TIME) VALUES
    (100005,100001,'2021-12-02 00:10:05.000000'),                       --100016
    (100005,100002,'2021-12-02 00:11:06.000000'),                       --100017
    (100006,100000,'2021-11-16 20:13:05.000000'),                       --100018
    (100006,100003,'2021-11-17 20:13:05.000000');                       --100019

INSERT INTO VOTE (USER_ID, RESTAURANT_ID) VALUES
    (100005,100001),                                                    --100020
    (100006,100002);                                                    --100021