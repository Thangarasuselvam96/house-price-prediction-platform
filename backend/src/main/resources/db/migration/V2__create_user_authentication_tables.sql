CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,

   first_name VARCHAR(100) NOT NULL,

   last_name VARCHAR(100) NOT NULL,

   email VARCHAR(255) NOT NULL UNIQUE,

   password VARCHAR(255) NOT NULL,

   phone_number VARCHAR(20),

   status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

   updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (

   id BIGSERIAL PRIMARY KEY,

   name VARCHAR(50) NOT NULL UNIQUE

);

CREATE TABLE user_roles (

    user_id BIGINT NOT NULL,

    role_id BIGINT NOT NULL,

    PRIMARY KEY(user_id, role_id),

    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(id),

    CONSTRAINT fk_role
        FOREIGN KEY(role_id)
            REFERENCES roles(id)

);

INSERT INTO roles(name)
VALUES
    ('USER'),
    ('ADMIN');