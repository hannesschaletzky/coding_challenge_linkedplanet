CREATE TABLE connections (
    id SERIAL PRIMARY KEY,
    source_device_name VARCHAR(100) NOT NULL,
    target_device_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (source_device_name) REFERENCES devices(name),
    FOREIGN KEY (target_device_name) REFERENCES devices(name)
);
