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


INSERT INTO ROLES(name) VALUES ('ROLE_USER');
INSERT INTO ROLES(name) VALUES ('ROLE_ADMIN');

INSERT INTO USERS(username, email, password) VALUES
    ('test', 'test@test.com', '{bcrypt}$2a$10$blG3I2eS34cCEJfNOxYtx.V2U4HPcv1LF4J4t.5tcKZ/eFd71LYw6');

INSERT INTO USER_ROLES(user_id, role_id) VALUES (1, 2);