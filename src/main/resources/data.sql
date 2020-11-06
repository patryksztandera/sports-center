INSERT INTO client (id, name, surname, email, password, phone)
VALUES (1, 'admin', 'ADMIN', 'admin@mail.com', '$2a$10$v41LTDo9HsBzfQiMIv7Is.VITHrdS1vG23vhAbkqkh557YAPBJMbW', '00000000');

INSERT INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_USER');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);
