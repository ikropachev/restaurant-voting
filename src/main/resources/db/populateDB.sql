DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD, PRIVILEGED) VALUES
    ('Admin', 'admin@gmail.com', '{noop}admin_pass', TRUE),             -- 100000
    ('First_user', 'first@gmail.com', '{noop}first_pass', FALSE),       -- 100001
    ('Second_user', 'second@gmail.com', '{noop}second_pass', FALSE);    -- 100002

INSERT INTO RESTAURANTS (NAME) VALUES
    ('Burgers'),                                                        -- 100003
    ('HappyShaverma'),                                                  -- 100004
    ('Kavkaz');                                                         -- 100005

INSERT INTO DISHES (DESCRIPTION, PRICE, RESTAURANT_ID) VALUES
    ('Meat Burger', 100, 100003),                                       -- 100006
    ('Vegan Burger', 150, 100003),                                      -- 100007
    ('Chicken Burger', 50, 100003),                                     -- 100008
    ('Meat Shaverma', 100, 100004),                                     -- 100009
    ('Vegan Shaverma', 150, 100004),                                    -- 100010
    ('Chicken Shaverma', 50, 100004),                                   -- 100011
    ('Hachapuri po adzharski', 350, 100005),                            -- 100012
    ('Hachapuri po megrelski', 450, 100005),                            -- 100013
    ('Hachapuri po imeretinski', 550, 100005);                          -- 100014