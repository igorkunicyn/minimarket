INSERT INTO roles (name)
VALUES('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (password, username, email)
VALUES('$2y$10$aifbpkp5RvG7R91/Ldr0LuZu3A5ldqBA9k4QiWrJN8zeAGcGsGxbq','admin', 'admin@mail.ru');

INSERT INTO users_roles (user_id, role_id)
VALUES ('1','1'), ('1','2');

INSERT INTO categories (name)
VALUES ('Продукты питания'),('Бытовая техника');

INSERT INTO products (price, category_id, title_english, title_russian)
VALUES(56.00, '1', 'milk','молоко'),
(450.50, '1','meat', 'мясо'),
(720.00,'1','fish', 'рыба'),
(123.63,'1','apple','яблоки'),
(1500.00,'2','grill','гриль'),
(2500.50,'2','toaster','тостер'),
(1200.00,'2','teapot','чайник'),
(1800.00,'2','mixer','миксер');

INSERT INTO product_images (path, product_id)
VALUES('milk.jpg','1'),
('meat.jpg','2'),
('fish.jpg','3'),
('apple.jpg','4'),
('grill.jpg', '5'),
('toaster.jpg','6'),
('teapot.jpg','7'),
('mixer.jpg','8');
