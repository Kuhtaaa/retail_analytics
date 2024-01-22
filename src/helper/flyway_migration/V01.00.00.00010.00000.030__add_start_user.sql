INSERT INTO users (email, name, password, username)
VALUES (
        'user@gmail.com',
        'user',
        '$2a$12$RNHvdM.0KqBZVz6O.LPS7uDP.I2uYXU48A8BbqgLE0tY9U90Tr0sK',
        'user_name'),
    (
     'admin@gmail.com',
     'admin',
     '$2a$12$w0z2kXwZEkG8Ez2z1SdTaev2EYNSGi.hyvZZgHMWHdWHGk9MOxV9O',
     'admin_name'
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),(2, 2);