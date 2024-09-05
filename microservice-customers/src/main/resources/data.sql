-- Mock data for customers with hashed MD5 passwords
INSERT INTO customer (id, name, age, phone_num, pass) VALUES (1, 'John Doe', 30, '123-456-7890', MD5('password01'));
INSERT INTO customer (id, name, age, phone_num, pass) VALUES (2, 'Jane Smith', 25, '987-654-3210', MD5('password01'));
INSERT INTO customer (id, name, age, phone_num, pass) VALUES (3, 'Alice Johnson', 28, '555-555-5555', MD5('password01'));
