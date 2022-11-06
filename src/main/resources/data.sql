INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (4, 'ELECTRONIC', 'Camera', 135000, '/image/camera.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (64, 'ELECTRONIC', 'headphone', 40000, '/image/headphone.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (27, 'FASHION', 'Dress', 40000, '/image/dress.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (129, 'BOOK', 'Fountain Pen', 50000, '/image/fountain_pen.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (2, 'ELECTRONIC', 'MacBook', 3500000, '/image/macbook.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (53, 'FASHION', 'Necklace', 5000, '/image/Necklace.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (102, 'ELECTRONIC', 'Mouse', 20000, '/image/Mouse.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (252, 'BOOK', 'Crayon', 4000, '/image/crayon.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (53, 'FASHION', 'Sneakers', 79000, '/image/Sneakers.jpg');
INSERT INTO PRODUCTS(amount, category, name, price, thumnail) VALUES
    (8, 'ELECTRONIC', 'SmartWatch', 150000, '/image/SmartWatch.jpg');

INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (4, 'WAIT', 7, 350000, '2022-11-02 14:57:10');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (1, 'PROCESSING', 1, 135000, '2022-11-06 22:10:32');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (3, 'COMPLETED', 10, 400000, '2022-11-03 08:10:58');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (8, 'REJECTED', 49, 196000, '2022-11-06 15:27:32');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (4, 'WAIT', 35, 1750000, '2022-11-01 16:20:37');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (5, 'COMPLETED', 1, 3500000, '2022-11-01 15:19:53');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (4, 'REJECTED', 16, 2400000, '2022-10-25 10:12:22');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (6, 'PROCESSING', 28, 140000, '2022-10-31 22:52:18');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (2, 'WAIT', 3, 120000, '2022-11-04 12:49:35');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (7, 'COMPLETED', 84, 1680000, '2022-10-30 13:50:47');
INSERT INTO ORDERS(product_id, state, amount, price, order_dt) VALUES
    (9, 'PROCESSING', 12, 948000, '2022-10-26 17:04:06');



INSERT INTO ROLES(name) VALUES ('ROLE_USER');
INSERT INTO ROLES(name) VALUES ('ROLE_ADMIN');

INSERT INTO USERS(username, email, password) VALUES
    ('test', 'test@test.com', '{bcrypt}$2a$10$blG3I2eS34cCEJfNOxYtx.V2U4HPcv1LF4J4t.5tcKZ/eFd71LYw6');

INSERT INTO USER_ROLES(user_id, role_id) VALUES (1, 2);