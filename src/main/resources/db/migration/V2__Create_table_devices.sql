CREATE TABLE devices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    device_type_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (device_type_name) REFERENCES device_types(name)
);

