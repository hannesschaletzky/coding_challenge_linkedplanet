CREATE TABLE connections (
    id SERIAL PRIMARY KEY,
    source integer[] NOT NULL,
    target integer[] NOT NULL
);
