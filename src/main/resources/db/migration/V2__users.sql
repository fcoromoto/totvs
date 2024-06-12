
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
                       updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);