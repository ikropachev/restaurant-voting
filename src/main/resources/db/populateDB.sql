DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANTS (NAME) VALUES
    ('Nowhere'),                                                        -- 100000
    ('Burgers'),                                                        -- 100001
    ('HappyShaverma'),                                                  -- 100002
    ('Kavkaz');                                                         -- 100003

INSERT INTO USERS (NAME, EMAIL, PASSWORD, PRIVILEGED) VALUES
    ('Admin', 'admin@gmail.com', 'admin_pass', TRUE),                   -- 100004
    ('First_user', 'first@gmail.com', 'first_pass', FALSE),             -- 100005
    ('Second_user', 'second@gmail.com', 'second_pass', FALSE);          -- 100006

INSERT INTO DISHES (NAME, PRICE, RESTAURANT_ID) VALUES
    ('Meat Burger', 100, 100001),                                      -- 100007
    ('Vegan Burger', 150, 100001),                                     -- 100008
    ('Chicken Burger', 50, 100001),                                    -- 100009
    ('Meat Shaverma', 100, 100002),                                    -- 100010
    ('Vegan Shaverma', 150, 100002),                                   -- 100011
    ('Chicken Shaverma', 50, 100002),                                  -- 100012
    ('Hachapuri po adzharski', 350, 100003),                           -- 100013
    ('Hachapuri po megrelski', 450, 100003),                           -- 100014
    ('Hachapuri po imeretinski', 550, 100003);                         -- 100015