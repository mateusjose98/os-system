-- Create tb_user table
CREATE TABLE tb_user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Create tb_role table
CREATE TABLE tb_role (
    id SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

-- Create tb_user_role table
CREATE TABLE tb_user_role (
    user_id INTEGER REFERENCES tb_user(id) ON DELETE CASCADE,
    role_id INTEGER REFERENCES tb_role(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);
